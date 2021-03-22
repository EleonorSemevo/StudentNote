package com.memoire.studentnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.classes.MesEnfants;
import com.memoire.studentnote.database.Current;
import com.memoire.studentnote.emplois.TableEmplois;

import java.util.List;
//import androidx.recyclerview.widget.RecyclerView.ViewHolder;

class EcoleRecyclerAdapter extends  RecyclerView.Adapter <EcoleRecyclerAdapter.ViewHolder>{


    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<MesEnfants> mMesEnfants;

    public EcoleRecyclerAdapter(Context context, List<MesEnfants> mesEnfants)
    {
        mContext = context;
        mMesEnfants = mesEnfants;
        mLayoutInflater =LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_ecole_liste, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MesEnfants mesEnfants =mMesEnfants.get(position);
        holder.mNomEcole.setText(mesEnfants.getNomEcole());
        holder.mLocationEcole.setText(mesEnfants.getQuartierEcole()+" "+mesEnfants.getVilleEcole());
        holder.mClasse.setText(mesEnfants.getClasse());
        holder.mCurrentPosition = position;

    }




    @Override
    public int getItemCount() {
        return mMesEnfants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mNomEcole;
        public final TextView mLocationEcole;
        public int mCurrentPosition;
        public final TextView mClasse;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNomEcole = itemView.findViewById(R.id.item_action);
            mLocationEcole = itemView.findViewById(R.id.item_localisation);
            mClasse = itemView.findViewById(R.id.textview_classe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent intent=new Intent(mContext, ActionMenu.class);
                    Current.carteEmplois = mMesEnfants.get(mCurrentPosition);
//                    mContext.startActivity(intent);
                    Intent intent = new Intent(mContext, TableEmplois.class);
                    mContext.startActivity(intent);

                }
            });
        }
    }
}
