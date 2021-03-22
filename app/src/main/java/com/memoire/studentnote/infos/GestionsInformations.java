package com.memoire.studentnote.infos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.InformationRecyclerAdapter;
import com.memoire.studentnote.MenuTable;
import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Information;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.enseignant.AjoutNote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mEnfantRecyclerAdapter;
import static com.memoire.studentnote.database.DatabaseUtil.mEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mFirebaseAuth;
import static com.memoire.studentnote.database.DatabaseUtil.mListeEcoles;
import static com.memoire.studentnote.database.DatabaseUtil.mesEnfants;

public class GestionsInformations extends AppCompatActivity {

    private List<Information> mInformation;
    private GestionInfoRecyclerAdapter mGestionInfoRecyclerAdapter;
    private Spinner mSpinnerEcoles;

    private FloatingActionButton mFloatingActionButtonAjout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestions_informations);
        //mEditTextMessage = findViewById(R.id.edittext_ges_message);

        mFloatingActionButtonAjout = findViewById(R.id.floatingActionButton_info_add);
        mSpinnerEcoles = findViewById(R.id.spinner_infos_ecoles);
        initialiserSpinner();



        initialiseDisplayContent();

        mFloatingActionButtonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouInfos();
            }
        });



        mSpinnerEcoles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInformation.clear();
                mInformation = mDataManager.getInfosSelonIdcoles(mListeEcoles.get(position).getId());
                mGestionInfoRecyclerAdapter.nouvelleEcole(mInformation);
               // Toast.makeText(GestionsInformations.this,position+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(GestionsInformations.this,"nothing ooh",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initialiserSpinner()
    {
        List<String> ecoles = new ArrayList<>();
        for(int a=0;a<mListeEcoles.size();a++)
        {
            ecoles.add(mListeEcoles.get(a).getNom());
        }
        ArrayAdapter<String> ecolesAdapter = new ArrayAdapter<String>(GestionsInformations.this, android.R.layout.simple_spinner_item, ecoles);
        ecolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerEcoles.setAdapter(ecolesAdapter);
    }

    private void initialiseDisplayContent()
    {
        final RecyclerView recyclerView= ((RecyclerView)findViewById(R.id.info_gestion_recyvlerview));
        final LinearLayoutManager infoLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(infoLayoutManager);


        DataManager dataManager = DataManager.getInstance();

        mInformation =new ArrayList<>();
        mInformation = dataManager.getInfosSelonIdcoles(mListeEcoles.get(0).getId());
        //Completions des infos de classe
        List<Ecole> ecoles = dataManager.getEcoles();
        for(int ac = 0; ac< mInformation.size(); ac++)
        {
            for(int ad = 0;ad<ecoles.size();ad++)
            {
                if(mInformation.get(ac).getIdEcole()==ecoles.get(ad).getId())
                    mInformation.get(ac).setEcole(ecoles.get(ad).getNom());
            }
        }


        mGestionInfoRecyclerAdapter = new GestionInfoRecyclerAdapter(this, mInformation);
        recyclerView.setAdapter(mGestionInfoRecyclerAdapter);


    }

    public void  ajouInfos()
    {
        final int idEcole=0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ajout d'information");
        builder.setCancelable(true);
       // Context context =v.getContext();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.ajout_gest_infos, null);
        builder.setView(layout);
        builder.setIcon(R.drawable.icon);

       // final Information information = mInformation.get(mCurrentPosition);
        final EditText msg = layout.findViewById(R.id.edittext_ges_message);
       // msg.setText(information.getDescription());
       // msg.setEnabled(false);

        final int r;
        builder.setPositiveButton("Enregistrer",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String description = msg.getText().toString();
                        if(description.equals(""))
                        {
                            dialog.cancel();
                        }
                        else {
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE,1);
                            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");

                            mDataWorker.insertInformations(mEnseignant.getId(), mListeEcoles.get(mSpinnerEcoles.getSelectedItemPosition()).getId(),description,  format1.format(cal.getTime()));
                            mGestionInfoRecyclerAdapter.add(mDataManager.getLastInformation());
                        }


                    }
                });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }

}