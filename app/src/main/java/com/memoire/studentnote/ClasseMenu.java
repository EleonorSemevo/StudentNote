package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.database.Current;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

public class ClasseMenu extends AppCompatActivity {

    private ClasseRecyclerAdapter mClasseRecyclerAdapter;
    public  static final String ID_ECOLE="com.memoire.studentnote.ID_ECOLE";
    //public int mEcolePosition;
    private List<Ecole> mEcoles=new ArrayList<>();
    private List<Classe> mClasses;



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
        mClasses = new ArrayList<>();
        DataManager dm = DataManager.getInstance();
        //valeureRecu();
        //mEcoles = dm.getEcoles();

        mClasses =dm.classeFromEcole();


        mClasseRecyclerAdapter = new ClasseRecyclerAdapter(this, mClasses);
        recyclerView.setAdapter(mClasseRecyclerAdapter);
    }


//    public void valeureRecu()
//    {
//        Intent intent = getIntent();
//        mEcolePosition = intent.getIntExtra(ID_ECOLE,-1);
//        if(mEcolePosition==-1)
//        {
//            createNewEcole();
//        }
//
//    }

//    private void createNewEcole()
//    {
//        DataManager dm =DataManager.getInstance();
//        mEcolePosition = dm.createNewEcole();
//    }


}
