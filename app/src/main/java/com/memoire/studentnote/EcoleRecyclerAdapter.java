package com.memoire.studentnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.database.Current;

import java.util.List;
//import androidx.recyclerview.widget.RecyclerView.ViewHolder;

class EcoleRecyclerAdapter extends  RecyclerView.Adapter <EcoleRecyclerAdapter.ViewHolder>{

    private static final String ECOLE_ACTUElLE="ACTUELLE_ECOLE";
    private RecyclerView.ViewHolder mHolder;
    private int mPosition;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Ecole> mEcoles;

    public EcoleRecyclerAdapter(Context context, List<Ecole> ecoles)
    {
        mContext = context;
        mEcoles = ecoles;
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
        Ecole ecole =mEcoles.get(position);
        holder.mNomEcole.setText(ecole.getNom());
        holder.mLocationEcole.setText(ecole.getVille()+" "+ecole.getQuartier());
        holder.mCurrentPosition = position;

    }




    @Override
    public int getItemCount() {
        return mEcoles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mNomEcole;
        public final TextView mLocationEcole;
        public int mCurrentPosition;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNomEcole = itemView.findViewById(R.id.item_action);
            mLocationEcole = itemView.findViewById(R.id.item_localisation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment=new Fragment();
                    Intent intent=new Intent(mContext, ActionMenu.class);
                    Current.currentIdEcole = mEcoles.get(mCurrentPosition).getId();
                    //intent.putExtra(ActionMenu.ID_ECOLE, mEcoles.get(mCurrentPosition).getId());
                    mContext.startActivity(intent);

                }
            });
        }
    }
}
