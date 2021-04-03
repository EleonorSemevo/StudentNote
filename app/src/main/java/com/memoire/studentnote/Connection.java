package com.memoire.studentnote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;
import com.memoire.studentnote.database.DatabaseUtil;

import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;
import static com.memoire.studentnote.database.DatabaseUtil.mEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mListeEcoles;
import static com.memoire.studentnote.database.DatabaseUtil.mUtilisateurActuel;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;

public class Connection extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private EditText mEmail;
    private EditText mPassword;
    private Button mConnecter;
    private Button mEnregistrerSecond;
    private static final String GOOGLE_APPLICATION_CREDENTIALS="student-8cf9c-firebase-adminsdk-mzivv-9b346a73e2.json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mConnecter = findViewById(R.id.connecter);
        mEnregistrerSecond = findViewById(R.id.enregistrer_second);
        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mdb==null)
        {
            mDatabaseOpenHelper = new DatabaseOpenHelper(this);
            mdb= mDatabaseOpenHelper.getReadableDatabase();
            mDataWorker = new DatabaseDataWorker(mdb);
        }
        //mDataWorker.insPar();//A enlever apres //insertion d'un parent au début de l'application
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
            mDataWorker.ins();


        mConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connection();

            }
        });
        mEnregistrerSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Connection.this,Inscription.class);
                startActivity(intent);
            }
        });

    }




    private void connection()
    {
        final String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if(email.isEmpty() || password.isEmpty())
        {
            //Message d'erreure
        }
        else
        {
            mFirebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("", "signInWithEmail:success");
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                Toast.makeText(Connection.this, "Authentication réussie",
                                        Toast.LENGTH_SHORT).show();


                                //Enrégistrement de quelques information


                                DatabaseUtil.mUtilisateurActuel= mDataManager.getUserSelonMail(email);
                                if(mUtilisateurActuel.getType().equals("Enseignant"))
                                {
                                    DatabaseUtil.isEnseignant=true;
                                    mEnseignant = mDataManager.getEnseignantDepuisMail(email);
                                    initialliseListeEcole();
                                }
                                else if(mUtilisateurActuel.getType().equals("Parent"))
                                {
                                    DatabaseUtil.isParent=true;
                                }

                                //updateUI();
                                if(mUtilisateurActuel.getType().equalsIgnoreCase("Parent"))
                                {
                                    Intent intent = new Intent(Connection.this, MenuTable.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Intent intent = new Intent(Connection.this, MenuEnseignant.class);
                                    startActivity(intent);
                                    finish();
                                }



                            } else {
                                // If sign in fails, display a message to the user.
                                //MESSAGE D'EREURE
                                Log.w("", "signInWithEmail:failure", task.getException());
                                Toast.makeText(Connection.this, "Mot de passe ou mail incorrect.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);

                            }


                        }
                    });
        }



    }

    private void initialliseListeEcole()
    {
        if(DatabaseUtil.isEnseignant)
        {

            mListeEcoles = mDataManager.getListeEcoleEnseignant(mEnseignant.getId());
        }
    }






}
