package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

public class MatiereMenu extends AppCompatActivity {

    private MatiereRecyclerAdapter mMatiereRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere_menu);
        getExtra();
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
        matieres.add(0,new Matiere(10,"Mathématiques"));
        matieres.add(1,new Matiere(11,"SPCT"));
        matieres.add(2,new Matiere(12,"Anglais"));
        matieres.add(3,new Matiere(13,"EPS"));
        matieres.add(4,new Matiere(14,"Histoire géographie"));
        matieres.add(5,new Matiere(15,"SVT"));


        mMatiereRecyclerAdapter = new MatiereRecyclerAdapter(this, matieres);
        recyclerView.setAdapter(mMatiereRecyclerAdapter);
    }

    private void getExtra()
    {
//        Intent intent = new Intent();
//        mCurrentIdClasse = intent.getStringExtra(ID_CURRENT_CLASSE);

    }
}
