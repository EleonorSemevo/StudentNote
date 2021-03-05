package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.memoire.studentnote.classes.Note;
import com.memoire.studentnote.essaies.MyRecyclerAdapter;
import com.memoire.studentnote.essaies.Notes;

import java.util.ArrayList;
import java.util.List;

public class NoteAfficher extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerAdapter mMyRecyclerAdapter;

    private List<Notes> mNotes;
    private List<Note> notes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_afficher);
        recyclerView = findViewById(R.id.recyclerview);
        initialisewithGrid();
    }

    public void initialisewithGrid()
    {
        List<Note> notes = new ArrayList<>();
        mNotes = new ArrayList<>();

        notes.add(0,new Note(1,1,1,1,1,"exercice1",
                "08:04:2020","semestre 1",2017,18));

        notes.add(1,new Note(2,1,1,1,1,"exercice2",
                "08:04:2020","semestre 1",2017,20));

        notes.add(2,new Note(2,1,1,1,1,"Interrogation 1",
                "08:04:2020","semestre 1",2017,20));
        notes.add(3,new Note(2,1,1,1,1,"Interrogation 2",
                "08:04:2020","semestre 1",2017,20));


        notes.add(4,new Note(2,1,1,1,1,"Devoir 1",
                "08:04:2020","semestre 1",2017,20));
        notes.add(5,new Note(2,1,1,1,1,"Devoir 2",
                "08:04:2020","semestre 1",2017,20));
        notes.add(6,new Note(2,1,1,1,1,"exercice 3",
                "08:04:2020","semestre 2",2017,20));
        notes.add(7,new Note(2,1,1,1,1,"exercice 4",
                "08:04:2020","semestre 1",2017,20));
        notes.add(8,new Note(2,1,1,1,1,"exercice 5",
                "08:04:2020","semestre 1",2017,20));

        mNotes.add(0,Notes.createSection(notes.get(0)));
        mNotes.add(1,Notes.createRow(notes.get(1)));
        mNotes.add(2, Notes.createRow(notes.get(2)));
        mNotes.add(3, Notes.createRow(notes.get(3)));

        mNotes.add(4, Notes.createRow(notes.get(4)));
        mNotes.add(5, Notes.createRow(notes.get(5)));

        mNotes.add(3, Notes.createSection(notes.get(6)));

        mNotes.add(3, Notes.createRow(notes.get(6)));
        mNotes.add(3, Notes.createRow(notes.get(7)));
        mNotes.add(3, Notes.createRow(notes.get(8)));

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