package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ActionMenu extends AppCompatActivity {
    private ActionRecyclerAdapter mActionRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu);
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
}
