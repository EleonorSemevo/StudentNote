package com.memoire.studentnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActionRecyclerAdapter extends RecyclerView.Adapter<ActionRecyclerAdapter.ViewHolder> {
    private RecyclerView.ViewHolder mHolder;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<String> mActions;

    public ActionRecyclerAdapter(Context context, List<String> actions)
    {
        mContext = context;
        mActions = actions;
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
        String texte = mActions.get(position);
        holder.mTextAction.setText(texte);
        holder.mCurrentPosition = position;

    }



    @Override
    public int getItemCount() {
        return mActions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTextAction;
        public int mCurrentPosition;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTextAction = itemView.findViewById(R.id.text_action);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mCurrentPosition==0)
                    {
                        Intent intent = new Intent(mContext, ClasseMenu.class);
                        mContext.startActivity(intent);
                    }


                }
            });
        }
    }
}
