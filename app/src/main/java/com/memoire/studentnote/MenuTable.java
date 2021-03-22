package com.memoire.studentnote;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Eleve;
import com.memoire.studentnote.classes.Etudier;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;
import com.memoire.studentnote.database.DatabaseUtil;
import com.memoire.studentnote.emplois.AjoutEmplois;
import com.memoire.studentnote.enseignant.AjoutNote;
import com.memoire.studentnote.infos.GestionsInformations;

import java.util.ArrayList;
import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.idEcoleActuelle_enseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;

import static com.memoire.studentnote.database.DatabaseUtil.mEnfantRecyclerAdapter;
import static com.memoire.studentnote.database.DatabaseUtil.mFirebaseAuth;
import static com.memoire.studentnote.database.DatabaseUtil.mListeClasses;
import static com.memoire.studentnote.database.DatabaseUtil.mListeEcoles;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;
import static com.memoire.studentnote.database.DatabaseUtil.mesEnfants;

public class MenuTable extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //For drawer
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    private FloatingActionButton boutton_ajouter;
    private TextView mMatricule;
    private Spinner mSpinner;
    private TextView mAnnee;

    //
    private List<Ecole> mEcoles;
    private List<Eleve> liste_complete_eleves = new ArrayList<>();
   // private TextView erroMessage;

//Les classes chargées
    private List<Ecole> m_ecoles =new ArrayList<>();
    private List<Eleve> m_eleves = new ArrayList<>();
    private List<Etudier> m_etudier =new ArrayList<>();

//Pour les enseignants
    private Spinner mSpinnerListeEcole;

 //   private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int[] tabIcons = {
            R.mipmap.eleve,
            R.mipmap.note,
            R.mipmap.message,
            R.mipmap.information,
            R.mipmap.avatar};
    private View mHeaderView;
    private List<String> mNomEcoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu_table);

        //- Configure all views

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();
        idEcoleActuelle();



        //        setSupportActionBar(toolbar);
        boutton_ajouter = findViewById(R.id.ajouter);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
         setupTabIcons();


   //     {
            mDatabaseOpenHelper = new DatabaseOpenHelper(this);
            mdb= mDatabaseOpenHelper.getReadableDatabase();
            mDataWorker = new DatabaseDataWorker(mdb);
 //       }


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
        adapter.addFragment(new EmploisFragment(), "Emplois");
        adapter.addFragment(new MessageFragment(),"Messages");
        adapter.addFragment(new InformationFragment(),"Informations");
        adapter.addFragment(new ProfilFragment(),"Profile");
        //adapter.addFragment(new BookFragment(), "Book");
        viewPager.setAdapter(adapter);

    }



//    public void simpleAlert() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        final AlertDialog alertDialog = builder.create();
//        //Récupération des éléments de l'alert
//        mMatricule =findViewById(R.id.matricule);
//        mSpinner = alertDialog.findViewById(R.id.list_ecoles);
//        //erroMessage = alertDialog.findViewById(R.id.error_ajout);
//        mAnnee = alertDialog.findViewById(R.id.annee);
//
//        //Récupération des données pour le spinner
//        final DataManager dataManager= DataManager.getInstance();
//        mEcoles = dataManager.getEcoles();
//        List<String> nomEcoles= new ArrayList<>();
//        for(int i=0;i<mEcoles.size();i++)
//        {
//            nomEcoles.add(mEcoles.get(i).getNom());
//        }
//
//       Log.d("**********",nomEcoles.toString());
//
//
//
//
//
//        //Insertions des données dans le spinner
//        ArrayAdapter<String> spinerAdapter = new ArrayAdapter<String>(MenuTable.this, android.R.layout.simple_spinner_item, nomEcoles);
//        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(spinerAdapter);
//
//
//        builder.setTitle("Ajouter enfant");
//        //builder.setMessage("We have a message");
//        builder.setPositiveButton("Ajouter",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                    }
//                });
//        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                alertDialog.dismiss();
//            }
//        });
//        builder.setCancelable(false);
//        builder.show();
//    }

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
        List<Etudier> listeEtudiers= mDataManager.getEtudiers();

        for(int m=0;m<listeEtudiers.size();m++)
        {
            if(listeEtudiers.get(m).getMatricule().equals(matricule))
                est_verifier =true;
            break;
        }

        return est_verifier;
    }

    public int  idDepuisMatricule(String matricule)
    {
        int idEleve=-1;
        List<Etudier> listeEtudiers= mDataManager.getEtudiers();
        for (int b=0;b<listeEtudiers.size();b++)
        {
            if(listeEtudiers.get(b).getMatricule().equals(matricule))
                idEleve=listeEtudiers.get(b).getIdEleve();
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


    ///Pour le menu principal

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();
        Intent note= new Intent(MenuTable.this, AjoutNote.class);

        switch (id){
            case R.id.menu_note :
                //this.startActivity(note);
                confirmerChoixEcoleEtDemarrerAjotNote();
                break;
            case R.id.menu_information:
                startActivity(new Intent(this, GestionsInformations.class));
                break;
            case R.id.menu_emploi_temps:
                startActivity(new Intent(this, AjoutEmplois.class));
                break;
            case R.id.se_deconnecter:
                logout();
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if(DatabaseUtil.isEnseignant)
            menu.findItem(R.id.menu_note).setEnabled(false);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
/////////////////////////////////////////////////////////////////////////////////
        mHeaderView = navigationView.inflateHeaderView(R.layout.menu_header);
        mSpinnerListeEcole = mHeaderView.findViewById(R.id.spinner_liste_enseigant_ecoles);

        mNomEcoles = new ArrayList<>();
        for(int i=0;i<mListeEcoles.size();i++)
        {
            mNomEcoles.add(mListeEcoles.get(i).getNom());
        }

        ArrayAdapter<String> spinerAdapter = new ArrayAdapter<String>(MenuTable.this, android.R.layout.simple_spinner_item, mNomEcoles);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerListeEcole.setAdapter(spinerAdapter);



    }



    private void idEcoleActuelle()
    {
        int a= mSpinnerListeEcole.getSelectedItemPosition();
        mSpinnerListeEcole.getSelectedItemId();
        if(mListeEcoles.size()!=0)
        {
            idEcoleActuelle_enseignant = mListeEcoles.get(a).getId();
        }

    }

    private void confirmerChoixEcoleEtDemarrerAjotNote()
    {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmez le choix d'école");
    // set the custom layout
    final View customLayout = getLayoutInflater().inflate(R.layout.layout_confirmer_ecole, null);
    final Spinner conf=customLayout.findViewById(R.id.ecoles_liste);


    ArrayAdapter<String> spinerAdapter = new ArrayAdapter<String>(MenuTable.this, android.R.layout.simple_spinner_item, mNomEcoles);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conf.setAdapter(spinerAdapter);
        builder.setView(customLayout);
    // add a button
        builder.setPositiveButton("Confirmez", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            int position = conf.getSelectedItemPosition();
           idEcoleActuelle_enseignant = mListeEcoles.get(position).getId();
           mSpinnerListeEcole.setSelection(position);
           Intent intent = new Intent(MenuTable.this,AjoutNote.class);
           startActivity(intent);



    };
    // create and show the alert dialog

    });
    AlertDialog dialog = builder.create();
    dialog.show();}

    private void logout()
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,Connection.class));
        finish();
    }

}
