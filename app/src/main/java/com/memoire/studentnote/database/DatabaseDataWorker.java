package com.memoire.studentnote.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.memoire.studentnote.classes.Enseigner;

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

    public void insertClasse(String nom,String idEcole)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ClasseEntry.COLUMN_ID, dm.sizeClasse()+"Classe");
        values.put(DatabaseContract.ClasseEntry.COLUMN_NOM, nom);
        values.put(DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE, idEcole);
        long newRowId = mdb.insert(DatabaseContract.ClasseEntry.TABLE_NAME,null, values);
    }

    public void insertMatiere(String nom)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MatiereEntry.COLUMN_ID,dm.sizeMatiere()+"Matiere");
        values.put(DatabaseContract.MatiereEntry.COLUMN_NOM,nom);
        long newRowId = mdb.insert(DatabaseContract.MatiereEntry.TABLE_NAME, null, values);

    }

    public void insertEnseigner(String idEcole, String idEnseignant, String idMatiere, String idClasse)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.EnseignerEntry.COLUMN_ID,dm.sizeEnseignant()+"Enseignant");
        values.put(DatabaseContract.EnseignerEntry.COLUMN_ID_ECOLE,idEcole);
        values.put(DatabaseContract.EnseignerEntry.COLUMN_ID_ENSEIGNANT, idEnseignant);
        values.put(DatabaseContract.EnseignerEntry.COLUMN_ID_MATIERE, idMatiere);
        values.put(DatabaseContract.EnseignerEntry.COLUMN_CLASSE,idClasse);


        long newRowId = mdb.insert(DatabaseContract.EnseignerEntry.TABLE_NAME, null, values);

    }



    //DONNEES STATIC CHARGER AU PREALABLE D4ABORS
    public void insClasse()
    {
        insertClasse("6eme","0Ecole");
        insertClasse("5eme","1Ecole");
        insertClasse("4eme","0Ecole");
        insertClasse("3eme A","1Ecole");
    }

    public void insMatiere()
    {
        insertMatiere("math");
        insertMatiere("Français");
        insertMatiere("Anglais");
        insertMatiere("Physique");
    }



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
    public void insEnseigner()
    {
        insertEnseigner("0Ecole","0Enseignant","0Matiere","0Classe");
        insertEnseigner("0Ecole","0Enseignant","1Matiere","0Classe");
        insertEnseigner("1Ecole","0Enseignant","2Matiere","1Classe");
        insertEnseigner("1Ecole","0Enseignant","3Matiere","1Classe");


    }

    public void ins()
    {
        insPar();
        insEc();

        insEns();
        insMatiere();
        insClasse();
        insEnseigner();
    }



}

