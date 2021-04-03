package com.memoire.studentnote.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.EnseignerT;

import java.util.List;

public class ProfilChatRecyclerAdapter extends RecyclerView.Adapter<ProfilChatRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<EnseignerT> mEnseignerTList;

    public ProfilChatRecyclerAdapter(Context context, List<EnseignerT> list)
    {
        mContext = context;
        mEnseignerTList = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_layout_lpm_profil, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EnseignerT enseignerT = mEnseignerTList.get(position);

        holder.mTextViewEcole.setText(enseignerT.getEcole().getNom());
        holder.mTextViewClasse.setText(enseignerT.getClasse().getNom());
        holder.mTextViewMatiere.setText(enseignerT.getMatiere().getNom());

    }

    @Override
    public int getItemCount() {
        return mEnseignerTList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextViewEcole;
        public final TextView mTextViewClasse;
        public final TextView mTextViewMatiere;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewClasse = itemView.findViewById(R.id.lpm_classe);
            mTextViewEcole = itemView.findViewById(R.id.lpm_ecole);
            mTextViewMatiere = itemView.findViewById(R.id.lpm_matiere);
        }
    }
}
