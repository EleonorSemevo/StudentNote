package com.memoire.studentnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.classes.Enfant;
import com.memoire.studentnote.classes.MesEnfants;
import com.memoire.studentnote.classes.Note;

import java.util.List;

public class EnfantRecyclerAdapter extends RecyclerView.Adapter<EnfantRecyclerAdapter.ViewHolder> {
    private RecyclerView.ViewHolder mHolder;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<MesEnfants> mEnfants;

    public EnfantRecyclerAdapter(Context context, List<MesEnfants> enfants) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mEnfants = enfants;
    }

    @NonNull
    @Override
    public EnfantRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_enfant, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EnfantRecyclerAdapter.ViewHolder holder, int position) {
        MesEnfants enfant = mEnfants.get(position);
        holder.enfantNomPrenom.setText(enfant.getNom()+" "+enfant.getPrenom());
        holder.enfantClasse.setText(enfant.getClasse());
        holder.enfantEcole.setText(enfant.getNomEcole());
        String letter = enfant.getNom().charAt(0)+"";
        holder.lettre.setText(letter);

        holder.mCurrentPosition = position;


    }

    @Override
    public int getItemCount() {
        return mEnfants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView enfantNomPrenom ;
        public final TextView enfantEcole;
        public final TextView enfantClasse;
        public final TextView lettre;
        public int mCurrentPosition;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            enfantNomPrenom = itemView.findViewById(R.id.nom_enfant);
            enfantClasse = itemView.findViewById(R.id.classe_enfant);
            enfantEcole = itemView.findViewById(R.id.ecole_enfant);
            lettre = itemView.findViewById(R.id.lettre);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,MatiereMenu.class);
                    intent.putExtra(MatiereMenu.NOM, mEnfants.get(mCurrentPosition).getNom()+" "+mEnfants.get(mCurrentPosition).getPrenom());

                    intent.putExtra(MatiereMenu.ID_ELEVE, mEnfants.get(mCurrentPosition).getIdEleve());
                    intent.putExtra(MatiereMenu.ID_CLASSE, mEnfants.get(mCurrentPosition).getIdclasse());
                    intent.putExtra(MatiereMenu.ID_ECOLE, mEnfants.get(mCurrentPosition).getIdEcole());

                    mContext.startActivity(intent);

                }
            });
        }
    }
}
