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

    public void insertEcole(String nom,String ville, String quartier, String type, String code)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.EcoleEntry.COLUMN_ID, dm.sizeEcole()+"Ecole");
        values.put(DatabaseContract.EcoleEntry.COLUMN_NOM, nom);
        values.put(DatabaseContract.EcoleEntry.COLUMN_QUARTIER, quartier);
        values.put(DatabaseContract.EcoleEntry.COLUMN_VILLE, ville);
        values.put(DatabaseContract.EcoleEntry.COLUMN_TYPE, type);
        values.put(DatabaseContract.EcoleEntry.COLUMN_CODE, code);

        long newRowId = mdb.insert(DatabaseContract.EcoleEntry.TABLE_NAME,null, values);

    }

    public void insertEnseignant(String nom, String prenom, String mail, String password, String telephone)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.EnseignantEntry.COLUMN_ID,dm.sizeEnseignant()+"Enseignant");
        values.put(DatabaseContract.EnseignantEntry.COLUMN_NOM,nom);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_PRENOM, prenom);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_MAIL, mail);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_TELEPHONE,telephone);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_MDP, password);

        long newRowId = mdb.insert(DatabaseContract.EnseignantEntry.TABLE_NAME, null, values);

    }


    //DONNEES STATIC CHARGER AU PREALABLE D4ABORS

    public void insPar()
    {
        insertParent("Semevo","Eleonor","lorensemevo@gmail.com","tatatata","65707651");
    }

    public void insEc()
    {

        insertEcole("Ecole de Tankpe","Calavi","Tankpe","primaire","pi04");
        insertEcole("CEG KPAHOU", "Calavi","kpahou","CEG","ccaa");
    }
    public void insEns()
    {
        insertEnseignant("Tomavo","Clarisse","fleursemevo@gmail.com","tatatata","95209390");
    }



}

