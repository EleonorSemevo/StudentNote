package com.memoire.studentnote;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;

public class Inscription extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private EditText mNom;
    private EditText mPrenom;
    private EditText mMail;
    private EditText mMdp;
    private EditText mTelephone;
    private TextView mError;
    private Button mInscription;

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
        //mDatabaseOpenHelper = new DatabaseOpenHelper(this);
        //mDataWorker = new DatabaseDataWorker(mdb);
        mDatabaseOpenHelper = new DatabaseOpenHelper(this);
        mdb= mDatabaseOpenHelper.getReadableDatabase();
        mDataWorker = new DatabaseDataWorker(mdb);



        mInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });


    }

    private void createAccount()
    {
        final String email;
        final String password;
        final String nom;
        final String prenom;
        final String telephone;


        email = mMail.getText().toString();
        password =mMdp.getText().toString();

        //
        nom = mNom.getText().toString();
        prenom = mPrenom.getText().toString();
        telephone = mTelephone.getText().toString();

        if(email.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty())
        {
            mError.setText("Les champs(*) sont obligatoires");
        }


        else
        {
            if(password.length()< 6)
            {
                mError.setText("Mot de passe d'au moins 6 caractères");

            }

            Log.d("identifiants:",email+" "+password);
            mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Enregistrement des données dans la base de données
                                if(telephone==null)
                                {
                                    mDataWorker.insertParent(nom,prenom,email,password,"");
                                }
                                else
                                {
                                    mDataWorker.insertParent(nom,prenom,email,password,telephone);

                                }
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("user email", "createUserWithEmail:success");
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                Intent intent = new Intent(Inscription.this,MenuTable.class);
                                startActivity(intent);
                                // updateUI(user);
                            } else {
                                mError.setText("Erreur connexion");
                            }

                            // ...
                        }
                    });
        }


    }

    private void account()
    {

    }


}
