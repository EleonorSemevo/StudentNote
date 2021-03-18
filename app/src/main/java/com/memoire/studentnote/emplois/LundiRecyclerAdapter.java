package com.memoire.studentnote.emplois;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Emplois;

import java.util.List;

public class LundiRecyclerAdapter extends RecyclerView.Adapter<LundiRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private LayoutInflater mLayoutInflater;
    private final List<Emplois> mEmplois;

    public LundiRecyclerAdapter(Context context, List<Emplois>emploisList)
    {
        mContext = context;
        mEmplois = emploisList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.layout_emplois_item_lundi, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Emplois emplois = mEmplois.get(position);
        holder.mHeureDebut.setText(emplois.getHeure_debut());
        holder.mHeureFin.setText(emplois.getHeure_fin());
        holder.mEnseignant.setText(emplois.getNomEnseignant());
        holder.mMatiere.setText(emplois.getNomMatiere());
        holder.mCurrentPosition=position;
    }



    @Override
    public int getItemCount() {
        return mEmplois.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView mHeureDebut;
        public final TextView mHeureFin;
        public final TextView mMatiere;
        public final TextView mEnseignant;
        public int mCurrentPosition;

        public ViewHolder(View itemView){
            super(itemView);
            mHeureDebut = itemView.findViewById(R.id.heure_debut);
            mHeureFin = itemView.findViewById(R.id.heure_fin);
            mMatiere = itemView.findViewById(R.id.nom_matiere);
            mEnseignant = itemView.findViewById(R.id.nom_enseignant);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
