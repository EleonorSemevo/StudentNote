package com.memoire.studentnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.database.Current;

import java.util.List;

public class ClasseRecyclerAdapter extends RecyclerView.Adapter<ClasseRecyclerAdapter.ViewHolder> {
    private RecyclerView.ViewHolder mHolder;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<Classe> mClasses;

    public ClasseRecyclerAdapter(Context context, List<Classe> classes)
    {
        mContext = context;
        mClasses = classes;
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
        Classe classe = mClasses.get(position);
        holder.mClasse.setText(classe.getNom());
        holder.mCurrentPosition = position;

    }



    @Override
    public int getItemCount() {
        return mClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mClasse;
        public int mCurrentPosition;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mClasse = itemView.findViewById(R.id.text_action);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(mContext, MatiereMenu.class);
                    //intent.putExtra(MatiereMenu.ID_CURRENT_CLASSE,mClasses.get(mCurrentPosition).getId());
                    Current.currentIdClasse = mClasses.get(mCurrentPosition).getId();
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
