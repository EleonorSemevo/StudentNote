package com.memoire.studentnote;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.memoire.studentnote.classes.Information;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    private InformationRecyclerAdapter mInformationRecyclerAdapter;
    private List<Information> mInformation;


    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseDisplayContent(view);
    }

    private void initialiseDisplayContent(View view)
    {
        final RecyclerView recyclerView= ((RecyclerView)view.findViewById(R.id.info_recyvlerview));
        final LinearLayoutManager infoLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(infoLayoutManager);


        DataManager dataManager = DataManager.getInstance();

        mInformation=new ArrayList<>();
        mInformation = dataManager.getInformation();


        mInformationRecyclerAdapter = new InformationRecyclerAdapter(view.getContext(),mInformation);
        recyclerView.setAdapter(mInformationRecyclerAdapter);


    }

}
