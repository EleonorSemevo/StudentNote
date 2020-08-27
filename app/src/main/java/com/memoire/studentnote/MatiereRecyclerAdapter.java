package com.memoire.studentnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.classes.Matiere;

import java.util.List;

public class MatiereRecyclerAdapter extends RecyclerView.Adapter<MatiereRecyclerAdapter.ViewHolder> {
    private RecyclerView.ViewHolder mHolder;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Matiere> mMatieres;

    public MatiereRecyclerAdapter(Context context, List<Matiere> matieres)
    {
        mContext = context;
        mMatieres = matieres;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = mLayoutInflater.inflate(R.layout.item_action,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Matiere matiere = mMatieres.get(position);
        holder.mTextMatiere.setText(matiere.getNom());
        holder.mCurrentPosition = position;

    }



    @Override
    public int getItemCount() {
        return mMatieres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTextMatiere;
        public int mCurrentPosition;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTextMatiere = itemView.findViewById(R.id.text_action);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
        }
    }
}
