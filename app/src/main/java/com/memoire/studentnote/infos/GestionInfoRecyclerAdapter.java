package com.memoire.studentnote.infos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.InformationRecyclerAdapter;
import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Information;

import java.util.ArrayList;
import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mEnfantRecyclerAdapter;
import static com.memoire.studentnote.database.DatabaseUtil.mFirebaseAuth;
import static com.memoire.studentnote.database.DatabaseUtil.mesEnfants;

public class GestionInfoRecyclerAdapter extends RecyclerView.Adapter<GestionInfoRecyclerAdapter.ViewHolder>{
    private RecyclerView.ViewHolder mHolder;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<Information> mInformation=new ArrayList<>();

    public GestionInfoRecyclerAdapter(Context context, List<Information> information)
    {
        mContext = context;
        for(int u=0;u<information.size();u++)
        {
         mInformation.add(0,information.get(u));
        }
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GestionInfoRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = mLayoutInflater.inflate(R.layout.layout_information_item,parent, false);
        return new GestionInfoRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //String texte = mInformation.get(position);
        holder.mTextDate.setText(mInformation.get(position).getDatePublication());
        holder.mTextMessage.setText(mInformation.get(position).getDescription());
        // holder.mTextMessage.setEnabled(false);
        holder.mTextAuteur.setText(mInformation.get(position).getAuteur());
        holder.mTextEcole.setText(mInformation.get(position).getEcole());
        holder.mCurrentPosition = position;

    }





    @Override
    public int getItemCount() {
        return mInformation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTextDate;
        public final TextView mTextMessage;
        public final TextView mTextAuteur;
        public final TextView mTextEcole;
        public int mCurrentPosition;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTextDate = itemView.findViewById(R.id.date_view);
            mTextMessage = itemView.findViewById(R.id.message_view);
            mTextAuteur = itemView.findViewById(R.id.auteur_view);
            mTextEcole = itemView.findViewById(R.id.ecole_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Mise à jour");
                    builder.setCancelable(true);
                    Context context =v.getContext();
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.ajout_gest_infos, null);
                    builder.setView(layout);
                    builder.setIcon(R.drawable.icon);

                    final Information information = mInformation.get(mCurrentPosition);
                    final EditText msg = layout.findViewById(R.id.edittext_ges_message);
                    msg.setText(information.getDescription());

                    final int r;
                    builder.setPositiveButton("Enregistrer",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(!msg.getText().toString().equalsIgnoreCase(mInformation.get(mCurrentPosition).getDescription()))
                                    //update here

                                    {
                                        int r =mDataWorker.updateInformation(information.getId(), msg.getText().toString());
                                        mInformation.get(mCurrentPosition).setDescription(msg.getText().toString());

                                        notifyItemChanged(mCurrentPosition);
                                        Log.d("UPDATE", r +"");

                                    }

                                }
                            });
                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();


                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Vous voulez suprimer ce message, êtes vous sûre?");
                    builder.setCancelable(true);
                    Context context =v.getContext();
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.ajout_gest_infos, null);
                    builder.setView(layout);
                    builder.setIcon(R.drawable.icon);

                    final Information information = mInformation.get(mCurrentPosition);
                    final EditText msg = layout.findViewById(R.id.edittext_ges_message);
                    msg.setText(information.getDescription());
                    msg.setEnabled(false);

                    final int r;
                    builder.setPositiveButton("Supprimer",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //delete here
                                    mDataWorker.deleteInformation(information.getId());
                                    mInformation.remove(mCurrentPosition);
                                    notifyItemRemoved(mCurrentPosition);

                                }
                            });

                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;


                }
            });
        }


    }
    public List<Information> getInformation()
    {
        return mInformation;
    }

    public void setInformation(Information information) {
        mInformation.add(0,information);
        notifyItemInserted(0);
    }
    public  void  add(Information information)
    {
        mInformation.add(0,information);
        notifyItemInserted(0);
    }

    public void nouvelleEcole(List<Information> informations)
    {
        mInformation.clear();
        for(int u=0;u<informations.size();u++)
        {
            mInformation.add(0,informations.get(u));
        }
        notifyDataSetChanged();

    }
}
