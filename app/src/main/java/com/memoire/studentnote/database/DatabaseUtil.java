package com.memoire.studentnote.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.auth.FirebaseAuth;

public class DatabaseUtil {
    private Context context;

    private DatabaseUtil(){}
    public static DatabaseOpenHelper mDatabaseOpenHelper;
    public static SQLiteDatabase mdb;
    public static DatabaseDataWorker mDataWorker;
    public static DataManager mDataManager;
    public static boolean isEnseignant;
    public static boolean isParent;
    public static Cursor mCursor;
    public static Object CurrentUser;
    public static FirebaseAuth mFirebaseAuth;





    public  void getInstance()
    {
//        mdb= mDatabaseOpenHelper.getReadableDatabase();
//        mDatabaseOpenHelper = new DatabaseOpenHelper(context);
//        mDataWorker = new DatabaseDataWorker(mdb);
        //mDataManager = DataManager.getInstance();
        isEnseignant=false;
        isParent=false;


    }


}
