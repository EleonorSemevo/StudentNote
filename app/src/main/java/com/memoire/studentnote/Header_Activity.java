package com.memoire.studentnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.classes.Etudier;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseUtil;
import com.memoire.studentnote.enseignant.AjoutNote;

import java.util.ArrayList;
import java.util.List;

public class Header_Activity extends AppCompatActivity {
    Spinner mSpinnerEcoles;
    List<String> mListeecoles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_header);
        mSpinnerEcoles = findViewById(R.id.spinner_liste_enseigant_ecoles);

        geEcolesSelonEnseignant();
        for(int c=0;c<DatabaseUtil.mListeEcoles.size();c++)
        {
            mListeecoles.add(DatabaseUtil.mListeEcoles.get(c).getNom());
        }

        ArrayAdapter<String> ecoleAdapter = new ArrayAdapter<String>(Header_Activity.this, android.R.layout.simple_spinner_item, mListeecoles);
        ecoleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerEcoles.setAdapter(ecoleAdapter);



    }

    public void geEcolesSelonEnseignant()
    {
        List<Enseigner> enseigners = DataManager.getInstance().getEnseigners();
        List<Ecole> ecoles =DataManager.getInstance().getEcoles();
        List<Integer> idEcoles = new ArrayList<>();

        for(int i=0;i<enseigners.size();i++)
        {
            if(enseigners.get(i).getIdEnseignant()== DatabaseUtil.mEnseignant.getId())
            {
               idEcoles.add(enseigners.get(i).getIdEcole());
            }
        }

        for(int t=0;t<idEcoles.size();t++)
        {
            for (int j=0;j<ecoles.size();j++)
            {
                if(idEcoles.get(t)==ecoles.get(j).getId())
                    DatabaseUtil.mListeEcoles.add(ecoles.get(j));
            }
        }

    }
}