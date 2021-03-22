package com.memoire.studentnote;


import android.content.ContentValues;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.MesEnfants;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseContract;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.mdb;
import static com.memoire.studentnote.database.DatabaseUtil.mesEnfants;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmploisFragment extends Fragment {
    private EcoleRecyclerAdapter mEcoleRecyclerAdapter;

    public EmploisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    private void initialiseDisplayContent(View view)
    {
        final RecyclerView recyclerView= ((RecyclerView)view.findViewById(R.id.ecole_recycler_view));
        final LinearLayoutManager ecoleLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(ecoleLayoutManager);

        List <Ecole> ecoles= DataManager.getInstance().getEcoles();

        //Ajout des quartiers pour pouvoir les afficher
      for(int i=0;i<mesEnfants.size();i++)
      {
          for(int j=0;j<ecoles.size();j++)
          {
              if(mesEnfants.get(i).getIdEcole()==ecoles.get(j).getId())
              {
                  mesEnfants.get(i).setQuartierEcole(ecoles.get(j).getQuartier());
                  mesEnfants.get(i).setVilleEcole(ecoles.get(j).getVille());
              }

          }
      }

      //Enlever les redondances

        List<MesEnfants> mesEnfantsList= new ArrayList<>();

      for(int az=0;az<mesEnfants.size();az++)
      {
          if (!mesEnfantsList.contains(mesEnfants.get(az)))
              mesEnfantsList.add(mesEnfants.get(az));
      }



        mEcoleRecyclerAdapter = new EcoleRecyclerAdapter(view.getContext(),mesEnfantsList);
        recyclerView.setAdapter(mEcoleRecyclerAdapter);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseDisplayContent(view);
    }
}
