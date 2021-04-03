package com.memoire.studentnote;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.memoire.studentnote.chat.UserDetails;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;
import com.memoire.studentnote.database.DatabaseUtil;
import com.memoire.studentnote.pojo.Api;
import com.memoire.studentnote.pojo.RegisterResponse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;
import static com.memoire.studentnote.database.DatabaseUtil.mEnfantRecyclerAdapter;
import static com.memoire.studentnote.database.DatabaseUtil.mEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mFirebaseAuth;
import static com.memoire.studentnote.database.DatabaseUtil.mListeClasses;
import static com.memoire.studentnote.database.DatabaseUtil.mListeEcoles;
import static com.memoire.studentnote.database.DatabaseUtil.mUtilisateurActuel;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;
import static com.memoire.studentnote.database.DatabaseUtil.mesEnfants;

public class Inscription extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private EditText mNom;
    private EditText mPrenom;
    private EditText mMail;
    private EditText mMdp;
    private EditText mTelephone;
    private TextView mError;
    private Button mInscription;
    private Spinner mType;

    //
    private List<Ecole> mEcoles;
    private Spinner mSpinnerEcoles;
    private EditText mEditTextCode;
    //
    boolean mBooleanBon = false;
    private int idEc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mNom = findViewById(R.id.nom);
        mPrenom = findViewById(R.id.prenom);
        mMail = findViewById(R.id.Mail);
        mMdp = findViewById(R.id.mdp);
        mTelephone = findViewById(R.id.telephone);
        mError = findViewById(R.id.error);
        mInscription = findViewById(R.id.inscription);
        mType = findViewById(R.id.type);

        ///
        if(!mdb.isOpen())
        {
            mDatabaseOpenHelper = new DatabaseOpenHelper(this);
            mdb= mDatabaseOpenHelper.getReadableDatabase();
            mDataWorker = new DatabaseDataWorker(mdb);
        }



        mInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void createAccount()
    {
        final String email;
        final String password;
        final String nom;
        final String prenom;
        final String telephone;
        final String type;


        email = mMail.getText().toString();
        password =mMdp.getText().toString();

        //
        nom = mNom.getText().toString();
        prenom = mPrenom.getText().toString();
        telephone = mTelephone.getText().toString();
        type = mType.getSelectedItem().toString();

        if(email.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty() || type.isEmpty())
        {
            mError.setText("Les champs(*) sont obligatoires");
        }

        else if(telephone.length()!=8)
        {
            mError.setText("Mauvais numéro de téléphone");
        }
        else if(password.length()< 6)
        {
            mError.setText("Mot de passe d'au moins 6 caractères");

        }


        else
        {
            if(type.equals("Enseignant") )
            {
                if(!mBooleanBon)
                    finishEnseignant();


            }
            else
                mBooleanBon = true;



            //Log.d("identifiants:",email+" "+password);
            if(mBooleanBon)
            {
                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //Enregistrement des données dans la base de données
                                    if(telephone==null)
                                    {
                                        mDataWorker.insertUser(nom,prenom,email,"",password,type);
                                        UserDetails.list_uid_Users.add(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                        mDataWorker.insertListeUser(FirebaseAuth.getInstance().getCurrentUser().getUid(),nom,prenom,email,type);
                                    }
                                    else
                                    {
                                        mDataWorker.insertUser(nom,prenom,email,telephone,password,type);
                                        mDataWorker.insertListeUser(FirebaseAuth.getInstance().getCurrentUser().getUid(),nom,prenom,email,type);
                                    }

                                    if(type.equals("Enseignant"))
                                    {
                                        DatabaseUtil.isEnseignant=true;
                                       int idEnseignant = (int) mDataWorker.insertEnseignant(nom,prenom,email,telephone,password);
                                        int idEnA =(int) mDataWorker.insertEnseigner(idEc, idEnseignant);
                                        mEnseignant = mDataManager.getEnseignantDepuisMail(email);
                                        initialliseListeEcole();
//                                    mListeEcoles = mDataManager.getListeEcoleEnseignant(mEnseignant.getId());
//                                    mListeMatiere = mDataManager.getListeMatieresEnseignant(mEnseignant.getId());
//                                    mListeClasses = mDataManager.getListeClassesEnseignant(mEnseignant.getId());
                                    }
                                    else if(type.equals("Parent"))
                                    {
                                        DatabaseUtil.isParent=true;
                                        mDataWorker.insertParent(nom,prenom,email,telephone,password);
                                    }
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d("user email", "createUserWithEmail:success");
                                    //FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                    //signUp();

                                    if(type.equalsIgnoreCase("Parent"))
                                    {
                                        Intent intent = new Intent(Inscription.this, MenuTable.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Intent intent = new Intent(Inscription.this, MenuEnseignant.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    // updateUI(user);
                                } else {
                                    mError.setText("Erreur connexion");
                                }

                                // ...
                            }
                        });
            }
            //signUp();
        }


    }

    private void account()
    {

    }

//    private void signUp() {
//        // display a progress dialog
//        final ProgressDialog progressDialog = new ProgressDialog(com.memoire.studentnote.Inscription.this);
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show(); // show progress dialog
//
//        // Api is a class in which we define a method getClient() that returns the API Interface class object
//        // registration is a POST request type method in which we are sending our field's data
//        // enqueue is used for callback response and error
//        (Api.getClient().registration(mNom.getText().toString().trim(),
//                mPrenom.getText().toString().trim(), mTelephone.getText().toString().trim(), mMail.getText().toString().trim(),
//                mMdp.getText().toString().trim(), mType.getSelectedItem().toString().trim(), mMdp.getText().toString().trim()
//        )).enqueue(new Callback<com.memoire.studentnote.pojo.RegisterResponse>() {
//            @Override
//            public void onResponse(Call<com.memoire.studentnote.pojo.RegisterResponse> call, Response<com.memoire.studentnote.pojo.RegisterResponse> response) {
//                // signUpResponsesData = response.body();
////                Toast.makeText(getApplicationContext(), response.body().getType(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Inscription.this,MenuTable.class);
//                startActivity(intent);
//                finish();
//
//            }
//
//            @Override
//            public void onFailure(Call<com.memoire.studentnote.pojo.RegisterResponse> call, Throwable t) {
//                mError.setText(t.getMessage());
//                progressDialog.dismiss();
//
//            }
//        });
//    }

    private void initialliseListeEcole()
    {
        if(DatabaseUtil.isEnseignant)
        {
            mListeEcoles = mDataManager.getListeEcoleEnseignant(mEnseignant.getId());
        }

    }

    public void finishEnseignant(){

        final DataManager dataManager= DataManager.getInstance();
        mEcoles = dataManager.getEcoles();
        List<String> nomEcoles= new ArrayList<>();
        nomEcoles.clear();
        for(int i=0;i<mEcoles.size();i++)
        {
            nomEcoles.add(mEcoles.get(i).getNom());
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation d'école");
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.layout_finish_inscription, null);
        mSpinnerEcoles=customLayout.findViewById(R.id.spinner_alert_ecoles);
        mEditTextCode = customLayout.findViewById(R.id.editetext_code);


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Inscription.this, android.R.layout.simple_spinner_item, nomEcoles);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerEcoles.setAdapter(spinnerAdapter);

        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String code = mEditTextCode.getText().toString().trim();
                 idEc =  mEcoles.get(mSpinnerEcoles.getSelectedItemPosition()).getId();

                if(code.equals(dataManager.getCodeEcole(idEc)))
                {

                  mBooleanBon = true;

                }
                else
                {
                    Toast.makeText(Inscription.this, "Mauvais code", Toast.LENGTH_LONG).show();
                }

                }



        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }



}
