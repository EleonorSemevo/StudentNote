package com.memoire.studentnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.chat.ChatProfil;
import com.memoire.studentnote.chat.Sender;
import com.memoire.studentnote.chat.UserDetails;
import com.memoire.studentnote.classes.EnseignerT;
import com.memoire.studentnote.classes.ListeUser;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

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
        holder.textViewType.setText(listeUser.getType());
    }

    @Override
    public int getItemCount() {
        return mListeUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewNom;
        public final TextView textViewType;

        public final TextView textViewLettre;

        public int mCurrentPosition;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.nom_user);
            textViewType = itemView.findViewById(R.id.type_user);


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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //profil utilisateur
                    if(mListeUsers.get(mCurrentPosition).getType().equals("Enseignant"))
                    {
                        Intent intent = new Intent(mContext, ChatProfil.class);
                        intent.putExtra(ChatProfil.CURRENT_MAIL_USER, mListeUsers.get(mCurrentPosition).getMail());
                        mContext.startActivity(intent);
                    }
                    return false;
                }
            });
        }
    }



}
