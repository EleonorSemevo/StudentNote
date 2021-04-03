package com.memoire.studentnote.essaies;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.memoire.studentnote.NoteAfficher;
import com.memoire.studentnote.R;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Notes> mData;

    public MyRecyclerAdapter(List<Notes> data) {
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_header, parent, false);

            v.findViewById(R.id.textView_description).setBackgroundColor(Color.parseColor("#00AFF0"));
            return new HviewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
            return new RviewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Notes notes = mData.get(position);
        if(notes.isRow()) {
            RviewHolder rviewHolder = (RviewHolder) holder;
            rviewHolder.mType.setText(notes.getRow().getType());
            rviewHolder.mNote.setText(String.valueOf(notes.getRow().getNote()));
        } else {
            HviewHolder hviewHolder =(HviewHolder) holder;
            hviewHolder.mDescription.setText(notes.getSection());



        }

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        Notes item = mData.get(position);
        if(!item.isRow()) {
            return 0;
        } else {
            return 1;
        }
    }


    public class HviewHolder extends RecyclerView.ViewHolder{
        public final TextView mDescription;

        public HviewHolder(@NonNull View itemView) {
            super(itemView);

            mDescription  = itemView.findViewById(R.id.textView_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public class RviewHolder extends RecyclerView.ViewHolder{
        public final TextView mNote;
        public final TextView mType;
        public RviewHolder(@NonNull View itemView) {
            super(itemView);
            mNote = itemView.findViewById(R.id.text_note);
            mType = itemView.findViewById(R.id.texte_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }



}
