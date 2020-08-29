package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.memoire.studentnote.database.Current;

import java.util.ArrayList;
import java.util.List;

public class ActionMenu extends AppCompatActivity {
    public final static String ID_ECOLE="com.memoire.studentnote.ID_ECOLE";
    private ActionRecyclerAdapter mActionRecyclerAdapter;
    private String idCurrentEcole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu);
        getExtra();
        initialiseDisplayContent();
    }

    private void initialiseDisplayContent()
    {
        final RecyclerView recyclerView = findViewById(R.id.action_recycler_view);
        final GridLayoutManager actionGridLayoutManager = new GridLayoutManager(this,
                1,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(actionGridLayoutManager);
        List<String> actions = new ArrayList<>();
        actions.add("Notes");
        actions.add("Actuallité");
        actions.add("Enseignants");
        actions.add("Message");

        mActionRecyclerAdapter = new ActionRecyclerAdapter(this, actions);
        recyclerView.setAdapter(mActionRecyclerAdapter);
    }

    private void getExtra()
    {
//        Intent intent = new Intent();
//        idCurrentEcole = intent.getStringExtra(ID_ECOLE);
//        Current.currentIdEcole = idCurrentEcole;
    }
}
