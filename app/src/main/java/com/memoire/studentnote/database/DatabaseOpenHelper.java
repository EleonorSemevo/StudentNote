package com.memoire.studentnote.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final int DATABASE_VERSION = 1;


    public DatabaseOpenHelper(@androidx.annotation.Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.ParentEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.NoteEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.AdministrationEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.ClasseEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.EcoleEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.EleveEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.EnseignantEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.EtudierEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.InformationEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.NoteDocumentEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.MatiereEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.EnseignerEntry.SQL_CREATE_TABLE);
        ///
        db.execSQL(DatabaseContract.MesEnfantsEntry.SQL_CREATE_TABLE);
        db.execSQL(DatabaseContract.UserEntry.SQL_CREATE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
