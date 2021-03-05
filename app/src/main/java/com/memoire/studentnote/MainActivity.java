package com.memoire.studentnote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import static com.memoire.studentnote.database.DatabaseUtil.mSharedPreferences;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT=3000;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        //ALLER A L'INTERFACE PRINCIPALE SI LA PERSONNE S4ETAIT CONNECTER AU MOINS UNE FOIS DEJA
//        if(mSharedPreferences==null)
//        {
//            mSharedPreferences =getSharedPreferences("studentNote",MODE_PRIVATE);
//
//        }

      // if (!mSharedPreferences.getBoolean("logged",false))


           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   Intent intent = new Intent(MainActivity.this, Connection.class);
                   startActivity(intent);
                   finish();
               }
           }, SPLASH_TIME_OUT);



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if(currentUser != null )
        {
//            Intent intent = new Intent(this,Tabbed.class);
//            startActivity(intent);
        }
        //updateUI(currentUser);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    private void updateUI(FirebaseUser currentUser)
    {

    }


}
