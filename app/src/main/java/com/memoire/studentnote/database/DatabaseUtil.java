package com.memoire.studentnote.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.EnfantRecyclerAdapter;
import com.memoire.studentnote.classes.MesEnfants;

import java.util.List;

public class DatabaseUtil {
    private Context context;

    private DatabaseUtil(){}
    public static DatabaseOpenHelper mDatabaseOpenHelper;
    public static SQLiteDatabase mdb;
    public static DatabaseDataWorker mDataWorker;
    public static DataManager mDataManager;
    public static boolean isEnseignant;
    public static boolean isParent;
    public static android.database.Cursor mCursor;
    public static Object CurrentUser;
    public static FirebaseAuth mFirebaseAuth;
    public static SharedPreferences mSharedPreferences;

    //Pour les recyclerviews

    public static List<MesEnfants> mesEnfants;
    public static EnfantRecyclerAdapter mEnfantRecyclerAdapter;





    public  void getInstance()
    {

        isEnseignant=false;
        isParent=false;


    }


}
