package com.memoire.studentnote.emplois;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.memoire.studentnote.InformationRecyclerAdapter;
import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Emplois;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.classes.Information;
import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

public class LundiFragment extends Fragment {

    private LundiRecyclerAdapter mLundiRecyclerAdapter;
    private List<Emplois> mEmplois;


    public LundiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lundi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseDisplayContent(view);
    }

    private void initialiseDisplayContent(View view) {
        final RecyclerView recyclerView = view.findViewById(R.id.lundi_recycler_view);
        final LinearLayoutManager lundiLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(lundiLayoutManager);


        mEmplois = updateEmplois();

        mLundiRecyclerAdapter = new LundiRecyclerAdapter(view.getContext(), mEmplois);
        recyclerView.setAdapter(mLundiRecyclerAdapter);
    }


public List<Emplois> updateEmplois()
{
    List<Emplois> emplois = DataManager.getInstance().getEmplois();
    List<Ecole> ecoles = DataManager.getInstance().getEcoles();
    List<Classe> classes = DataManager.getInstance().getClasses();
    List<Enseigner> enseigners = DataManager.getInstance().getEnseigners();
    List<Enseignant> enseignants = DataManager.getInstance().getEnseignants();
    List<Matiere> matieres = DataManager.getInstance().getMatiere();

    //Pour ecoles
    for(int i=0;i<emplois.size();i++)
    {
        for(int j=0;j<ecoles.size();j++)
        {
            if(emplois.get(i).getIdEcole()==ecoles.get(j).getId())
                emplois.get(i).setNomEcole(ecoles.get(j).getNom());
        }
    }

    //Pour matiere

    for(int a=0;a<emplois.size();a++)
    {
        for(int b=0;b<matieres.size();b++)
        {
            if(emplois.get(a).getIdMatiere()==matieres.get(b).getId())
                emplois.get(a).setNomMatiere(matieres.get(b).getNom());
        }
    }

    //pour classe

    for(int c=0; c<emplois.size();c++)
    {
        for(int d=0;d<classes.size();d++)
        {
            if(emplois.get(c).getIdClasse()==classes.get(d).getId())
                emplois.get(c).setNomClasse(classes.get(d).getNom());
        }
    }

    //enseignants

    for(int e=0;e<emplois.size();e++)
    {
        for(int f=0;f<enseigners.size();f++)
        {
            if(emplois.get(e).getIdClasse()==enseigners.get(f).getIdClasse() &&
                    emplois.get(e).getIdEcole() ==enseigners.get(f).getIdEcole() &&
                    emplois.get(e).getIdMatiere() == enseigners.get(f).getIdMatiere())
            {
                emplois.get(e).setIdEnseignant(enseigners.get(f).getIdEnseignant());
            }
        }
    }

    for(int g=0;g<emplois.size();g++)
    {
        for(int h=0;h<enseignants.size();h++)
        {
            if(emplois.get(g).getIdEnseignant()== enseignants.get(h).getId())
            {
                emplois.get(g).setNomEnseignant(enseignants.get(h).getNom()+" "+enseignants.get(h).getPrenom());
            }
        }
    }

    return emplois;

}
}