package com.memoire.studentnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Eleve;
import com.memoire.studentnote.classes.Etudier;
import com.memoire.studentnote.classes.MesEnfants;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;

import static com.memoire.studentnote.database.DatabaseUtil.mEnfantRecyclerAdapter;
import static com.memoire.studentnote.database.DatabaseUtil.mFirebaseAuth;
import static com.memoire.studentnote.database.DatabaseUtil.mSharedPreferences;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;
import static com.memoire.studentnote.database.DatabaseUtil.mesEnfants;

public class MenuTable extends AppCompatActivity {
    private FloatingActionButton boutton_ajouter;
    private TextView mMatricule;
    private Spinner mSpinner;
    //private DatabaseDataWorker mDataWorker;
    private TextView mAnnee;

    //
    private List<Ecole> mEcoles;
    private List<Eleve> liste_complete_eleves = new ArrayList<>();
   // private TextView erroMessage;

//Les classes chargées
    private List<Ecole> m_ecoles =new ArrayList<>();
    private List<Eleve> m_eleves = new ArrayList<>();
    private List<Etudier> m_etudier =new ArrayList<>();


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int[] tabIcons = {
            R.drawable.common_google_signin_btn_text_light,
            R.drawable.common_google_signin_btn_text_light_normal,
            R.drawable.common_google_signin_btn_icon_dark,
            R.drawable.common_google_signin_btn_icon_light,
            R.drawable.common_google_signin_btn_text_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_table);
        /////


        /////
        //        setSupportActionBar(toolbar);
        boutton_ajouter = findViewById(R.id.ajouter);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
         setupTabIcons();

         ///
        if(!mdb.isOpen())
        {
            mDatabaseOpenHelper = new DatabaseOpenHelper(this);
            mdb= mDatabaseOpenHelper.getReadableDatabase();
            mDataWorker = new DatabaseDataWorker(mdb);
        }

//        if(mSharedPreferences==null)
//        {
//            mSharedPreferences =getSharedPreferences("studentNote",MODE_PRIVATE);
//
//        }
//
//        SharedPreferences.Editor myEditor =mSharedPreferences.edit();
//        myEditor.putBoolean("logged",true);
//        myEditor.putString("mail", mFirebaseAuth.getCurrentUser().getEmail());
//        myEditor.apply();

        boutton_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialogButtonClicked();
            }
        });
    }

    private void setupTabIcons()
    {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EnfantFragment(), "Elèves");
        adapter.addFragment(new NoteFragment(), "Notes");
        adapter.addFragment(new MessageFragment(),"Messages");
        adapter.addFragment(new InformationFragment(),"Informations");
        adapter.addFragment(new ProfilFragment(),"Profile");
        //adapter.addFragment(new BookFragment(), "Book");
        viewPager.setAdapter(adapter);

    }



    public void simpleAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        //Récupération des éléments de l'alert
        mMatricule =findViewById(R.id.matricule);
        mSpinner = alertDialog.findViewById(R.id.list_ecoles);
        //erroMessage = alertDialog.findViewById(R.id.error_ajout);
        mAnnee = alertDialog.findViewById(R.id.annee);

        //Récupération des données pour le spinner
        final DataManager dataManager= DataManager.getInstance();
        mEcoles = dataManager.getEcoles();
        List<String> nomEcoles= new ArrayList<>();
        for(int i=0;i<mEcoles.size();i++)
        {
            nomEcoles.add(mEcoles.get(i).getNom());
        }

       Log.d("**********",nomEcoles.toString());





        //Insertions des données dans le spinner
        ArrayAdapter<String> spinerAdapter = new ArrayAdapter<String>(MenuTable.this, android.R.layout.simple_spinner_item, nomEcoles);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinerAdapter);


        builder.setTitle("Ajouter enfant");
        //builder.setMessage("We have a message");
        builder.setPositiveButton("Ajouter",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showAlertDialogButtonClicked() {
        // create an alert builder

        final DataManager dataManager= DataManager.getInstance();
        mEcoles = dataManager.getEcoles();
        List<String> nomEcoles= new ArrayList<>();
        nomEcoles.clear();
        for(int i=0;i<mEcoles.size();i++)
        {
            nomEcoles.add(mEcoles.get(i).getNom());
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajouter un enfant");
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.layout_ajout_enfant, null);
        mSpinner=customLayout.findViewById(R.id.list_ecoles);
        mMatricule = customLayout.findViewById(R.id.matricule);
        mAnnee = customLayout.findViewById(R.id.annee);

        ArrayAdapter<String> spinerAdapter = new ArrayAdapter<String>(MenuTable.this, android.R.layout.simple_spinner_item, nomEcoles);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinerAdapter);

        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String matricule = mMatricule.getText().toString().trim();
                String nomEcole = mSpinner.getSelectedItem().toString().trim();

        //Déclaration des parametres nécessaires

                int idEleve;
                int idEcole;
                int idClasse;
                String nom;
                String prenom;
                String classe;

                initialiser();


                if(matricule.length()==0 || !eleveexiste(matricule) )
                {
                    sendDialogDataToActivity("Matricule invalide");
                }
                if( mAnnee.getText().toString().trim().length()<=3)
                {
                    sendDialogDataToActivity("Année mal saisie");
                }
                else
                {
                    idEleve = idDepuisMatricule(matricule);
                    idEcole = idDepuisEcole(nomEcole);
                    int annee = Integer.parseInt(mAnnee.getText().toString().trim());
                    if(verifier(idEleve, idEcole,annee))
                    {
                        //Mise à jours de l'interface/
                        sendDialogDataToActivity("Bonnes informations");
                        //Initialisations des parametres pour ajouter un nouveau enfant

                        if(mFirebaseAuth==null)
                            mFirebaseAuth= FirebaseAuth.getInstance();

                        String mail = mFirebaseAuth.getCurrentUser().getEmail();
                        int idParent = dataManager.getParentFromMail(mail);
                        nom = dataManager.getNomEleve(idEleve);
                        prenom = dataManager.getPrenomEleve(idEleve);
                        idClasse = dataManager.getIdClasseFromEtudier(idEleve,annee);
                        classe = dataManager.getNomClasseFromClasse(idClasse);


                        //Mise à jour de la liste des enfants
                        
                        mesEnfants.clear();
                        mDataWorker.insertMesEnfants(idParent,idClasse,idEleve,nom,prenom,idEcole,nomEcole,classe);
                        mesEnfants.addAll(dataManager.getMesEnfants());
                        mEnfantRecyclerAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        sendDialogDataToActivity("Mauvaises infos");
                    }
                }


            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    // do something with the data coming from the AlertDialog
    private void sendDialogDataToActivity(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    public void initialiser()
    {
        mDataManager = DataManager.getInstance();
        m_ecoles.clear();
        m_etudier.clear();
        m_eleves.clear();
        m_ecoles =mDataManager.getEcoles();
        m_etudier = mDataManager.getEtudiers();
        m_eleves = mDataManager.getEleves();
        Log.d("******",m_eleves.toString());
        Log.d("******",m_etudier.toString());
        Log.d("******",m_ecoles.toString());
    }

    public boolean verifier(int idEleve, int idEcole, int annee)
    {
        //Obtention de idEleve;

        boolean est_verifier=false;
        for(int k=0;k<m_etudier.size();k++)
        {
            if(m_etudier.get(k).getIdEcole()==idEcole && m_etudier.get(k).getIdEleve()==idEleve && m_etudier.get(k).getAnnee()==annee)
                est_verifier=true;
            break;
        }

        if (est_verifier)
        {
            Log.d("est verifier","vrai,idEleve et idEcole existent");
        }
        else
        {
            Log.d("est verifier","vrai,idEleve et idEcole n'existent pas");
        }
        return  est_verifier;
    }

    public boolean eleveexiste(String matricule)
    {
        boolean est_verifier = false;
        for(int m=0;m<m_eleves.size();m++)
        {
            if(m_eleves.get(m).getMatricule().equals(matricule))
                est_verifier =true;
            break;
        }

        return est_verifier;
    }

    public int  idDepuisMatricule(String matricule)
    {
        int idEleve=0;
        for (int b=0;b<m_eleves.size();b++)
        {
            if(m_eleves.get(b).getMatricule().equals(matricule))
                idEleve=m_eleves.get(b).getId();
            break;
        }
        return idEleve;
    }

    public int idDepuisEcole(String nomEcole)
    {
        int idEcole=0;
        for(int c=0;c<m_ecoles.size();c++)
        {
            if(m_ecoles.get(c).getNom().equals(nomEcole))
                idEcole = m_ecoles.get(c).getId();
            break;
        }
        if (idEcole==0)
        {
            Log.d("ecole","Ecole existe");
        }
        else
        {
            Log.d("ecole","Ecole n'existe pas");
        }

        return idEcole;
    }




}
