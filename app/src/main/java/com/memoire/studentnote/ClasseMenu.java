package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

public class ClasseMenu extends AppCompatActivity {

    private ClasseRecyclerAdapter mClasseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe_menu);
        initialiseDisplayContent();

    }

    private void initialiseDisplayContent()
    {
        final RecyclerView recyclerView = findViewById(R.id.classe_recycler_view);
        final GridLayoutManager actionGridLayoutManager = new GridLayoutManager(this,
                1,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(actionGridLayoutManager);
        List<Classe> classes = new ArrayList<>();
        DataManager dm = DataManager.getInstance();
        //classes=dm.getClasse();
        // A enlever lorsque sa methode sera implémentée dans DM
        classes.add(new Classe("cl1","6eme A","E001"));
        classes.add(new Classe("cl2","5eme A","E001"));
        classes.add(new Classe("cl","4eme A","E001"));

        mClasseRecyclerAdapter = new ClasseRecyclerAdapter(this, classes);
        recyclerView.setAdapter(mClasseRecyclerAdapter);
    }
}
