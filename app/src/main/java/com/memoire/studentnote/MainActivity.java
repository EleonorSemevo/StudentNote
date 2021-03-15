package com.memoire.studentnote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;
import com.memoire.studentnote.database.DatabaseUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;
import static com.memoire.studentnote.database.DatabaseUtil.mEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mSharedPreferences;
import static com.memoire.studentnote.database.DatabaseUtil.mUtilisateurActuel;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT=3000;
    private FirebaseAuth mFirebaseAuth;
    private int SIGN_IN_REQUEST_CODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();


        if(mFirebaseAuth.getCurrentUser()==null)
        {
            //Si l'utilisateur n'est pas connecté

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intentConnection = new Intent(MainActivity.this, Connection.class);
                    startActivityForResult(intentConnection, SIGN_IN_REQUEST_CODE);
                }
            }, SPLASH_TIME_OUT);
        }
        else
        {
            //Si l'utilisateur est connecté

            Toast.makeText(this,"Bon retour "+mFirebaseAuth.getCurrentUser().getEmail().charAt(0),Toast.LENGTH_LONG).show();
            init();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent menuTableContent = new Intent(MainActivity.this, MenuTable.class);
                    startActivityForResult(menuTableContent, SIGN_IN_REQUEST_CODE);
                }
            }, SPLASH_TIME_OUT);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Bonne arrivée",
                        Toast.LENGTH_LONG)
                        .show();

            } else {
                Toast.makeText(this,
                        "Essayer plus tard",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }
    }

    public void init()
    {
        if(mdb==null)
        {
            mDatabaseOpenHelper = new DatabaseOpenHelper(this);
            mdb= mDatabaseOpenHelper.getReadableDatabase();
            mDataWorker = new DatabaseDataWorker(mdb);
        }

        String email= mFirebaseAuth.getCurrentUser().getEmail();
        DatabaseUtil.mUtilisateurActuel= mDataManager.getUserSelonMail(email);
        if(mUtilisateurActuel.getType().equals("Enseignant"))
        {
            DatabaseUtil.isEnseignant=true;
            mEnseignant = mDataManager.getEnseignantDepuisMail(email);
        }
        else if(mUtilisateurActuel.getType().equals("Parent"))
        {
            DatabaseUtil.isParent=true;

        }
    }
}
