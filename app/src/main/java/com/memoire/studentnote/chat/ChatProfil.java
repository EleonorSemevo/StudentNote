package com.memoire.studentnote.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.EnseignerT;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

public class ChatProfil extends AppCompatActivity {

    public static final String CURRENT_MAIL_USER = "MAIL";
    private TextView mTextViewNom;
    private TextView mTextViewPrenom;
    private RecyclerView mRecyclerView;
    private ProfilChatRecyclerAdapter mProfilChatRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profil_messsagerie);
        mTextViewNom = findViewById(R.id.lpm_user_nom);
        mTextViewPrenom  = findViewById(R.id.lpm_user_prenom);
        mRecyclerView = findViewById(R.id.recyclerview_lpm);

        initialiser();



    }

    public void initialiser()
    {
        //final RecyclerView recyclerView= ((RecyclerView)view.findViewById(R.id.ecole_recycler_view));
        final LinearLayoutManager profilLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(profilLayoutManager);


        String mail = getIntent().getStringExtra(CURRENT_MAIL_USER);
        Enseignant enseignant =DataManager.getInstance().getEnseignantDepuisMail(mail);
        //Nom et prenom de l'enseignant
        mTextViewNom.setText(enseignant.getNom());
        mTextViewPrenom.setText(enseignant.getPrenom());

        List<EnseignerT> enseignerTList = reduire(mEnseignerTList(mail));


        //final RecyclerView recyclerView= ((RecyclerView)view.findViewById(R.id.info_recyvlerview));
        mProfilChatRecyclerAdapter = new ProfilChatRecyclerAdapter(this, enseignerTList);
        mRecyclerView.setAdapter(mProfilChatRecyclerAdapter);


    }

    public List<EnseignerT> mEnseignerTList(String mail) {
        List<EnseignerT> enseigners =DataManager.getInstance().getEnseignerTs();
        List<EnseignerT> enseignerTList = new ArrayList<>();
        for(int i=0;i<enseigners.size();i++)
        {
            if(enseigners.get(i).getEnseignant().getMail().equals(mail))
                enseignerTList.add(enseigners.get(i));
        }
        Log.d("bbb",enseignerTList.toString());
        return enseignerTList;
    }

    public List<EnseignerT> reduire(List<EnseignerT> ens)
    {
        List<EnseignerT> ls = new ArrayList<>();
        for(int j=0;j<ens.size();j++)
        {
            if(!ls.contains(ens.get(j)))
                ls.add(ens.get(j));
        }
        return ls;
    }


}