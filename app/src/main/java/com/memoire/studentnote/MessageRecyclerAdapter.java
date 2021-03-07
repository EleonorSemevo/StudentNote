package com.memoire.studentnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.chat.Sender;
import com.memoire.studentnote.chat.UserDetails;
import com.memoire.studentnote.classes.ListeUser;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {
    private RecyclerView.ViewHolder mHolder;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<ListeUser> mListeUsers;

    public MessageRecyclerAdapter(Context context, List<ListeUser> mListeUsers) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mListeUsers = mListeUsers;
    }

    @NonNull
    @Override
    public MessageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_layout_chat_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageRecyclerAdapter.ViewHolder holder, int position) {
        ListeUser listeUser = mListeUsers.get(position);
        String nomComplet = listeUser.getNom()+" "+listeUser.getPrenom();

        holder.textViewNom.setText(nomComplet);
        //holder.textViewTime.setText(listeUser.getClasse());
        //holder.textViewMessage.setText(listeUser.getNomEcole());

        String letter = listeUser.getNom().charAt(0)+"";
        holder.textViewLettre.setText(letter);
        holder.mCurrentPosition=position;
    }

    @Override
    public int getItemCount() {
        return mListeUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewNom;
        public final TextView textViewMessage;
        public final TextView textViewTime;
        public final TextView textViewLettre;
        public int mCurrentPosition;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.nom_user);
            textViewTime = itemView.findViewById(R.id.message_time);
            textViewMessage = itemView.findViewById(R.id.last_msg);
            textViewLettre = itemView.findViewById(R.id.profile_lettre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext,MatiereMenu.class);
//                    mContext.startActivity(intent);

                    UserDetails.chatWith =  mListeUsers.get(mCurrentPosition).getUid();
                    Intent intent = new Intent(mContext,Sender.class);
                    mContext.startActivity(intent);



                }
            });
        }
    }
}
