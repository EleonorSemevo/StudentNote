package com.memoire.studentnote;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;

import static com.memoire.studentnote.database.DatabaseUtil.mEnfantRecyclerAdapter;
import static com.memoire.studentnote.database.DatabaseUtil.mesEnfants;

/**
 * A simple {@link Fragment} subclass.

 */
public class EnfantFragment extends Fragment {
   // private EnfantRecyclerAdapter mEnfantRecyclerAdapter;

    public EnfantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enfant, container, false);
    }

    private void initialiseDisplayContent(View view)
    {
        final RecyclerView recyclerView= ((RecyclerView)view.findViewById(R.id.enfant_recycler_view));
        final LinearLayoutManager enfantLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(enfantLayoutManager);

        //List <MesEnfants> mesEnfants= new ArrayList<>();
        DataManager dataManager = DataManager.getInstance();
        //A enlever
//        if(dataManager.sizeEcole()==0)
//        {
//            DatabaseUtil.mDataWorker.insEc1();
//            DatabaseUtil.mDataWorker.insEc2();
//        }
        //

        //mesEnfants =dataManager.getEcoles() ;
        mesEnfants=new ArrayList<>();
        //mesEnfants.add(0, new MesEnfants(1, 1,1,"SOTO TAMI","5eme",1,"CEG P",1,"5eme"));
        //mesEnfants.add(0, new MesEnfants(2, 1,2,"TAMI","4eme",1,"CEG P",1,"5eme"));
        mesEnfants = dataManager.getMesEnfants();
        Log.d("Liste enfan*********t", mesEnfants.toString());
        mEnfantRecyclerAdapter = new EnfantRecyclerAdapter(view.getContext(),mesEnfants);
        recyclerView.setAdapter(mEnfantRecyclerAdapter);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseDisplayContent(view);
    }
}