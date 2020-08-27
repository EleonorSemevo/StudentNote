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
        initialiseDisplayContent();
    }

    private void initialiseDisplayContent()
    {
        final RecyclerView recyclerView = findViewById(R.id.classe_recycler_view);
        final GridLayoutManager actionGridLayoutManager = new GridLayoutManager(this,
                1,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(actionGridLayoutManager);
        List<Matiere> matieres = new ArrayList<>();
        //A enlever lorque la DM sera à jour
        matieres.add(new Matiere("m1","math"));
        matieres.add(new Matiere("m2","français"));
        matieres.add(new Matiere("an2","anglais"));
        DataManager dm= DataManager.getInstance();
       // matieres = dm.getMatiere();


        mMatiereRecyclerAdapter = new MatiereRecyclerAdapter(this, matieres);
        recyclerView.setAdapter(mMatiereRecyclerAdapter);
    }
}
