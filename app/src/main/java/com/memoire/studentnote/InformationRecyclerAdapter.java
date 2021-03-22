package com.memoire.studentnote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.classes.Information;

import java.util.List;

public class InformationRecyclerAdapter extends RecyclerView.Adapter<InformationRecyclerAdapter.ViewHolder> {
    private RecyclerView.ViewHolder mHolder;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<Information> mInformation;

    public InformationRecyclerAdapter(Context context, List<Information> information)
    {
        mContext = context;
        mInformation = information;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = mLayoutInflater.inflate(R.layout.layout_information_item,parent, false);
        return new ViewHolder(itemView);
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
//                    if(mCurrentPosition==0)
//                    {
//                        Intent intent = new Intent(mContext, ClasseMenu.class);
//                        mContext.startActivity(intent);
//                    }


                }
            });
        }
    }
}
