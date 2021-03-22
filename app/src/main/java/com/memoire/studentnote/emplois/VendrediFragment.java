package com.memoire.studentnote.emplois;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Emplois;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;

public class VendrediFragment extends Fragment {

    private LundiRecyclerAdapter mLundiRecyclerAdapter;
    private List<Emplois> mEmplois;


    public VendrediFragment() {
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
        List<Emplois> lundi_emplois =new ArrayList<>() ;
        List<Emplois> emplois = DataManager.getInstance().getEmplois();
//   Log.d("tout emp",emplois.size()+"");
//   Log.d("r","ééééééééééééééééééééééééé"+emplois.size());


        //Choix des lundi_emplois du lundi
        for(int vu=0;vu<emplois.size();vu++)
        {
            if(emplois.get(vu).getJour().equals("Vendredi"))
                lundi_emplois.add(emplois.get(vu));
        }


        Log.d("début ooh",lundi_emplois.toString()+ lundi_emplois.size());

        List<Ecole> ecoles = DataManager.getInstance().getEcoles();
        // Log.d("ecoles",ecoles.toString()+ ecoles.size());

        List<Classe> classes = DataManager.getInstance().getClasses();
        //Log.d("classes",classes.toString()+ classes.size());

        List<Enseigner> enseigners = DataManager.getInstance().getEnseigners();
        //   Log.d("enseigners",enseigners.toString()+ enseigners.size());

        List<Enseignant> enseignants = DataManager.getInstance().getEnseignants();
        //   Log.d("enseignants",enseignants.toString()+ enseignants.size());

        List<Matiere> matieres = DataManager.getInstance().getMatiere();
        //  Log.d("matieres",matieres.toString()+ matieres.size());

        Log.d("tag","###############################################################################################################################################");

        //Pour ecoles
        for(int i=0;i<lundi_emplois.size();i++)
        {
            for(int j=0;j<ecoles.size();j++)
            {
                if(lundi_emplois.get(i).getIdEcole()==ecoles.get(j).getId())
                    lundi_emplois.get(i).setNomEcole(ecoles.get(j).getNom());
                //lundi_emplois.get(i).
            }
        }

        Log.d("ecole",lundi_emplois.toString()+ lundi_emplois.size());

        //Pour matiere

        for(int a=0;a<lundi_emplois.size();a++)
        {
            for(int b=0;b<matieres.size();b++)
            {
                if(lundi_emplois.get(a).getIdMatiere()==matieres.get(b).getId())
                    lundi_emplois.get(a).setNomMatiere(matieres.get(b).getNom());
            }
        }
        Log.d("matiere",lundi_emplois.toString()+ lundi_emplois.size());
        //pour classe

        for(int c=0; c<lundi_emplois.size();c++)
        {
            for(int d=0;d<classes.size();d++)
            {
                if(lundi_emplois.get(c).getIdClasse()==classes.get(d).getId())
                    lundi_emplois.get(c).setNomClasse(classes.get(d).getNom());
            }
        }
        Log.d("classe",lundi_emplois.toString()+ lundi_emplois.size());
        //enseignants

        for(int e=0;e<lundi_emplois.size();e++)
        {
            for(int f=0;f<enseigners.size();f++)
            {
                if(lundi_emplois.get(e).getIdClasse()==enseigners.get(f).getIdClasse() &&
                        lundi_emplois.get(e).getIdEcole() ==enseigners.get(f).getIdEcole() &&
                        lundi_emplois.get(e).getIdMatiere() == enseigners.get(f).getIdMatiere())
                {
                    lundi_emplois.get(e).setIdEnseignant(enseigners.get(f).getIdEnseignant());
                }
            }
        }

        for(int g=0;g<lundi_emplois.size();g++)
        {
            Log.d("tailles",enseignants.size()+"");
            Log.d("t","*********************");
            for(int h=0;h<enseignants.size();h++)
            {
                if(lundi_emplois.get(g).getIdEnseignant()== enseignants.get(h).getId())
                {
                    lundi_emplois.get(g).setNomEnseignant(enseignants.get(h).getNom()+" "+enseignants.get(h).getPrenom());
                }
            }
        }
        Log.d("final",lundi_emplois.toString()+ lundi_emplois.size());
        return lundi_emplois;

    }
}