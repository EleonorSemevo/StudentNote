package com.memoire.studentnote.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.EnfantRecyclerAdapter;
import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.classes.MesEnfants;
import com.memoire.studentnote.classes.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private Context context;

    private DatabaseUtil(){}
    public static DatabaseOpenHelper mDatabaseOpenHelper;
    public static SQLiteDatabase mdb;
    public static DatabaseDataWorker mDataWorker;
    public static DataManager mDataManager = DataManager.getInstance();
    public static boolean isEnseignant;
    public static boolean isParent;
    public static android.database.Cursor mCursor;
    public static Object CurrentUser;
    public static FirebaseAuth mFirebaseAuth;
    public static SharedPreferences mSharedPreferences;

    //Pour les recyclerviews

    public static List<MesEnfants> mesEnfants;
    public static EnfantRecyclerAdapter mEnfantRecyclerAdapter;


////Pour les enseignants;
    public static Enseignant mEnseignant;

//    public static int mIdEcole;
//    public static int mIdMatiere;
    public static List<Matiere> mListeMatiere= new ArrayList<>();
    public static List<Classe> mListeClasses = new ArrayList<>();
    public static List<Ecole> mListeEcoles= new ArrayList<>();
    public static int idEcoleActuelle_enseignant;

//// User
    public static User mUtilisateurActuel;





    public  void getInstance()
    {

        isEnseignant=false;
        isParent=false;


    }




}
