package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

public class MatiereMenu extends AppCompatActivity {

    public static final String NOM = "NOM";
    public static final String ID_ELEVE = "ID_ELEVE";
    public static final String ID_CLASSE = "ID_CLASSE";
    public static final String ID_ECOLE= "ID_ECOLE";
    String nom;
    int idEleve;
    int idClasse;
    int idEcole;

    private MatiereRecyclerAdapter mMatiereRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere_menu);
        //getExtra();
        initialiseDisplayContent();
    }

    private void initialiseDisplayContent()
    {
        final RecyclerView recyclerView = findViewById(R.id.classe_recycler_view);
        final GridLayoutManager actionGridLayoutManager = new GridLayoutManager(this,
                1,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(actionGridLayoutManager);
        List<Matiere> matieres = new ArrayList<>();

        DataManager dm= DataManager.getInstance();

        //matieres = dm.getMtiereOfClasseFromEcole();
        //matieres = dm.getMatieresFClasseFEcole();
        //matieres = dm.getMtiereOfClasseFromEcole();
        matieres.add(0,new Matiere(1,"Mathématiques"));
        matieres.add(1,new Matiere(2,"SPCT"));
        matieres.add(2,new Matiere(3,"Français"));
        matieres.add(3,new Matiere(4,"EPS"));
        matieres.add(4,new Matiere(5,"Histoire géographie"));
        matieres.add(5,new Matiere(6,"Anglais"));
        matieres.add(5,new Matiere(5,"SVT"));


        getExtra();
        mMatiereRecyclerAdapter = new MatiereRecyclerAdapter(this, matieres, nom,idEcole,idEleve,idClasse );
        Log.d("12345",nom+"chchch");
        recyclerView.setAdapter(mMatiereRecyclerAdapter);
    }

    private void getExtra()
    {
       Intent intent = getIntent();
        nom = intent.getStringExtra(NOM);
        idClasse = intent.getIntExtra(ID_CLASSE,0);
        idEleve = intent.getIntExtra(ID_ELEVE,0);
        idEcole =intent.getIntExtra(ID_ECOLE, 0);


    }
}
