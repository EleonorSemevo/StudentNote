package com.memoire.studentnote.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import static com.memoire.studentnote.database.DatabaseUtil.mdb;

public class DatabaseDataWorker {
//    private SQLiteDatabase mdb;
    private DataManager dm;

    public DatabaseDataWorker(SQLiteDatabase mdb) {
        //DatabaseUtil.mdb = mdb;
        dm = DataManager.getInstance();

    }

private void insertParent(String id,String nom, String prenom, String mail, String telephone, String password)
{
    ContentValues values = new ContentValues();
    values.put(DatabaseContract.ParentEntry.COLUMN_ID,id);
    values.put(DatabaseContract.ParentEntry.COLUMN_NOM,nom);
    values.put(DatabaseContract.ParentEntry.COLUMN_PRENOM, prenom);
    values.put(DatabaseContract.ParentEntry.COLUMN_MAIL, mail);
    values.put(DatabaseContract.ParentEntry.COLUMN_TELEPHONE,telephone);
    values.put(DatabaseContract.ParentEntry.COLUMN_MDP, password);

    long newRowId = mdb.insert(DatabaseContract.ParentEntry.TABLE_NAME, null, values);
}

    public void insertParent(String nom, String prenom, String mail, String password, String telephone)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ParentEntry.COLUMN_ID,dm.sizeParent()+"Parent");
        values.put(DatabaseContract.ParentEntry.COLUMN_NOM,nom);
        values.put(DatabaseContract.ParentEntry.COLUMN_PRENOM, prenom);
        values.put(DatabaseContract.ParentEntry.COLUMN_MAIL, mail);
        values.put(DatabaseContract.ParentEntry.COLUMN_TELEPHONE,telephone);
        values.put(DatabaseContract.ParentEntry.COLUMN_MDP, password);

        long newRowId = mdb.insert(DatabaseContract.ParentEntry.TABLE_NAME, null, values);

    }


}

