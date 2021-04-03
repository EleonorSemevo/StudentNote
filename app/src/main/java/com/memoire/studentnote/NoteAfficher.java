package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.memoire.studentnote.classes.Note;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.essaies.MyRecyclerAdapter;
import com.memoire.studentnote.essaies.Notes;

import java.util.ArrayList;
import java.util.List;

public class NoteAfficher extends AppCompatActivity {
    public static final String ID_ECOLE = "ID_ECOLE";
    public static final String ID_CLASSE="ID_CLASSE";
    public static final String ID_ELEVE="ID_ELEVE";

    public static String MATIERE="MATIERE";
    public static String NOM = "NOM";

    private RecyclerView recyclerView;
    private MyRecyclerAdapter mMyRecyclerAdapter;
    private EditText mEditTextNom;
    private EditText mEditTextMatiere;

    private List<Notes> mNotes;
    private List<Note> notes ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_afficher);
        recyclerView = findViewById(R.id.recyclerview);
        mEditTextMatiere = findViewById(R.id.textEditL_matiere);
        mEditTextNom = findViewById(R.id.textEditL_nom);
        entete();
        initialisewithGrid();
    }

    public void  entete()
    {
        Intent intent = getIntent();
        String nom = intent.getStringExtra(NOM);
        String matiere = intent.getStringExtra(MATIERE);
        mEditTextNom.setText(nom);
        mEditTextMatiere.setText(matiere);
    }

    public void initialisewithGrid()
    {
        List<Note> notes =  DataManager.getInstance().getNotes();
        mNotes = new ArrayList<>();


//        notes.add(0,new Note(1,1,1,1,1,"exercice1",
//                "08:04:2020","semestre 1",2017,18));
//
//        notes.add(1,new Note(2,1,1,1,1,"exercice2",
//                "08:04:2020","semestre 1",2017,11));
//
//        notes.add(2,new Note(2,1,1,1,1,"Interrogation 1",
//                "08:04:2020","semestre 1",2017,13));
//        notes.add(3,new Note(2,1,1,1,1,"Interrogation 2",
//                "08:04:2020","semestre 1",2017,8));
//
//
//        notes.add(4,new Note(2,1,1,1,1,"Devoir 1",
//                "08:04:2020","semestre 1",2017,14));
//        notes.add(5,new Note(2,1,1,1,1,"Devoir 2",
//                "08:04:2020","semestre 1",2017,16));
//        notes.add(6,new Note(2,1,1,1,1,"exercice 3",
//                "08:04:2020","semestre 2",2017,12));
//        notes.add(7,new Note(2,1,1,1,1,"exercice 4",
//                "08:04:2020","semestre 1",2017,12));
//        notes.add(8,new Note(2,1,1,1,1,"exercice 5",
//                "08:04:2020","semestre 1",2017,03));
//
//        mNotes.add(0,Notes.createSection(notes.get(0)));
//        mNotes.add(1,Notes.createRow(notes.get(1)));
//        mNotes.add(2, Notes.createRow(notes.get(2)));
//        mNotes.add(3, Notes.createRow(notes.get(3)));
//
//        mNotes.add(4, Notes.createRow(notes.get(4)));
//        mNotes.add(5, Notes.createRow(notes.get(5)));
//
//        mNotes.add(3, Notes.createSection(notes.get(6)));
//
//        mNotes.add(3, Notes.createRow(notes.get(6)));
//        mNotes.add(3, Notes.createRow(notes.get(7)));
//        mNotes.add(3, Notes.createRow(notes.get(8)));


        final List <String> description = new ArrayList<>();

        for(int i=0;i<notes.size();i++)
        {
            if(!description.contains(notes.get(i).getDescription()))
                description.add(notes.get(i).getDescription());

        }

        List <Note> notes1 = new ArrayList<>();
        for(int d=0;d<description.size();d++)
        {
            for(int e=0;e<notes.size();e++)
            {
                if(description.get(d).equals(notes.get(e).getDescription()))
                    notes1.add(notes.get(e));
            }
        }
        Log.d("descriptions", description.size()+"descrition");

        for(int f=0;f<notes1.size();f++)
        {
            if(f==0)
            {
                mNotes.add(Notes.createSection(notes.get(f)));
                mNotes.add(Notes.createRow(notes.get(f)));
            }
            else {
                if (notes1.get(f-1).getDescription().equals(notes1.get(f).getDescription()))
                {
                    mNotes.add(Notes.createRow(notes1.get(f)));
                }
                else
                {
                    mNotes.add(Notes.createSection(notes.get(f)));
                    mNotes.add(Notes.createRow(notes.get(f)));
                }
            }

            Log.d("mNotes",mNotes.size()+"mNotes");


        }



//        for(  int j=0;j<notes.size();j++)
//        {
//            String e="";
//            if(description.contains(notes.get(j).getDescription()))
//            {
//                for(int k=0;k<notes.size();k++)
//                {
//                    if(notes.get(k).getDescription().equals(description.get(j)))
//                        if(k==0)
//                            mNotes.add(Notes.createSection(notes.get(k)));
//                        mNotes.add(Notes.createRow(notes.get(k)));
//
//                     e=description.get(j);
//                }
//                description.remove(e);
//            }
//
//        }
        Log.d("oooh",mNotes.size()+"aaaaaaaaaaaaaaaa");


        mMyRecyclerAdapter = new MyRecyclerAdapter(mNotes);





        final GridLayoutManager actionGridLayoutManager = new GridLayoutManager(this,
                2,RecyclerView.VERTICAL,false);


        recyclerView.setLayoutManager(actionGridLayoutManager);

        recyclerView.setAdapter(mMyRecyclerAdapter);
        actionGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mNotes.get(position).isRow())
                    return 1;
                else
                    return 2;
            }
        });

        //List<Matiere> matieres = new ArrayList<>();
    }
}