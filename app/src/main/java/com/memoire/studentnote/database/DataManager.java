package com.memoire.studentnote.database;

import android.database.Cursor;


import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.classes.Note;
import com.memoire.studentnote.classes.Parent;
import com.memoire.studentnote.database.DatabaseContract.EnseignantEntry;
import com.memoire.studentnote.database.DatabaseContract.NoteEntry;
import com.memoire.studentnote.database.DatabaseContract.ParentEntry;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance=null;
    private List <Parent> mParents = new ArrayList<>();
    private List <Note> mNotes = new ArrayList<>();
    private List <Enseignant> mEnseignants = new ArrayList<>();
    private List <Ecole> mEcoles = new ArrayList<>();
    private List <Classe> mClasses = new ArrayList<>();
    private List <Matiere> mMatieres = new ArrayList<>();
    private List <Enseigner> mEnseigners = new ArrayList<>();


    public static DataManager getInstance()
    {
        if(ourInstance==null)
        {
            ourInstance = new DataManager();
        }
        return ourInstance;
    }

    public static void loadFromDatabase(DatabaseOpenHelper dbHelper)
    {
        //SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String[] parentColumns ={
                ParentEntry.COLUMN_ID,
                ParentEntry.COLUMN_NOM,
                ParentEntry.COLUMN_PRENOM,
                ParentEntry.COLUMN_MAIL,
                ParentEntry.COLUMN_MDP,
                ParentEntry.COLUMN_TELEPHONE,
                ParentEntry._ID
        };

        final Cursor parentCursor = DatabaseUtil.mdb.query(ParentEntry.TABLE_NAME,
                    parentColumns,null,null,null,null,null,null);
        loadFromDatabase((DatabaseOpenHelper) parentCursor);
///
        final String[] noteColums = {
                NoteEntry.COLUMN_ID_ECOLE,
                NoteEntry.COLUMN_ID_CLASSE,
                NoteEntry.COLUMN_ID_MATIERE,
                NoteEntry.COLUMN_ID_ELEVE,
                NoteEntry.COLUMN_TYPE,
                NoteEntry.COLUMN_DATE_COMPOSITION,
                NoteEntry.COLUMN_DESCRIPTION,
                NoteEntry._ID
        };

        final Cursor noteCursor = DatabaseUtil.mdb.query(NoteEntry.TABLE_NAME,
                noteColums,null,null,null,null,null,null);

        loadFromDatabase((DatabaseOpenHelper) noteCursor);
///

        final String[] enseignantColumns = new String[]{
                EnseignantEntry.COLUMN_ID,
                EnseignantEntry.COLUMN_NOM,
                EnseignantEntry.COLUMN_PRENOM,
                EnseignantEntry.COLUMN_MAIL,
                EnseignantEntry.COLUMN_MDP,
                EnseignantEntry.COLUMN_TELEPHONE,
                EnseignantEntry._ID

        };

        final Cursor enseignantCursor = DatabaseUtil.mdb.query(ParentEntry.TABLE_NAME,
                enseignantColumns,null,null,null,null,null,null);
        loadFromDatabase((DatabaseOpenHelper) enseignantCursor);


    }

    private void loadParentFromDatabase()
    {
        final String[] parentColumns ={
                ParentEntry.COLUMN_ID,
                ParentEntry.COLUMN_NOM,
                ParentEntry.COLUMN_PRENOM,
                ParentEntry.COLUMN_MAIL,
                ParentEntry.COLUMN_MDP,
                ParentEntry.COLUMN_TELEPHONE,
                ParentEntry._ID
        };

        final Cursor parentCursor = DatabaseUtil.mdb.query(ParentEntry.TABLE_NAME,
                parentColumns,null,null,null,null,null,null);

        int parentNomPos = parentCursor.getColumnIndex(ParentEntry.COLUMN_NOM);
        int parentPrenomPos = parentCursor.getColumnIndex(ParentEntry.COLUMN_PRENOM);
        int parentMailPos = parentCursor.getColumnIndex(ParentEntry.COLUMN_MAIL);
        int parentMdpPos = parentCursor.getColumnIndex(ParentEntry.COLUMN_MDP);
        int parentTelephone = parentCursor.getColumnIndex(ParentEntry.COLUMN_TELEPHONE);
        int parentId = parentCursor.getColumnIndex(ParentEntry.COLUMN_ID);

        DataManager dm =getInstance();
        dm.mParents.clear();
        while (parentCursor.moveToNext())
        {
            String nom = parentCursor.getString(parentNomPos);
            String prenom = parentCursor.getString(parentPrenomPos);
            String mail = parentCursor.getString(parentMailPos);
            String mdp = parentCursor.getString(parentMdpPos);
            String telephone = parentCursor.getString(parentTelephone);
            String id = parentCursor.getString(parentId);

            Parent parent = new Parent(id,nom,prenom,mail,mdp,telephone);
            dm.mParents.add(parent);
        }

        parentCursor.close();
    }

    private void loadEnseignantFromDatabase()
    {
        final String[] enseignantColumns ={
                EnseignantEntry.COLUMN_ID,
                EnseignantEntry.COLUMN_NOM,
                EnseignantEntry.COLUMN_PRENOM,
                EnseignantEntry.COLUMN_MAIL,
                EnseignantEntry.COLUMN_MDP,
                EnseignantEntry.COLUMN_TELEPHONE,
                EnseignantEntry._ID

        };

        final Cursor enseignantCursor = DatabaseUtil.mdb.query(ParentEntry.TABLE_NAME,
                enseignantColumns,null,null,null,null,null,null);
        //loadFromDatabase((DatabaseOpenHelper) enseignantCursor);

        int enseignantNomPos = enseignantCursor.getColumnIndex(ParentEntry.COLUMN_NOM);
        int enseignantPrenomPos = enseignantCursor.getColumnIndex(ParentEntry.COLUMN_PRENOM);
        int enseignantMailPos = enseignantCursor.getColumnIndex(ParentEntry.COLUMN_MAIL);
        int enseignantMdpPos = enseignantCursor.getColumnIndex(ParentEntry.COLUMN_MDP);
        int enseignantTelephone = enseignantCursor.getColumnIndex(ParentEntry.COLUMN_TELEPHONE);
        int enseignantId = enseignantCursor.getColumnIndex(ParentEntry.COLUMN_ID);

        DataManager dm =getInstance();
        dm.mParents.clear();
        while (enseignantCursor.moveToNext())
        {
            String nom = enseignantCursor.getString(enseignantNomPos);
            String prenom = enseignantCursor.getString(enseignantPrenomPos);
            String mail = enseignantCursor.getString(enseignantMailPos);
            String mdp = enseignantCursor.getString(enseignantMdpPos);
            String telephone = enseignantCursor.getString(enseignantTelephone);
            String id = enseignantCursor.getString(enseignantId);

            Enseignant enseignant = new Enseignant(id,nom,prenom,mail,mdp,telephone);
            dm.mEnseignants.add(enseignant);
        }

        enseignantCursor.close();
    }

    private void loadEcoleFromDatabase()
    {
        final String[] ecoleColums ={
                DatabaseContract.EcoleEntry.COLUMN_ID,
                DatabaseContract.EcoleEntry.COLUMN_NOM,
                DatabaseContract.EcoleEntry.COLUMN_VILLE,
                DatabaseContract.EcoleEntry.COLUMN_QUARTIER,
                DatabaseContract.EcoleEntry.COLUMN_TYPE,
                DatabaseContract.EcoleEntry.COLUMN_CODE

        };

        final Cursor ecoleCursor = DatabaseUtil.mdb.query(DatabaseContract.EcoleEntry.TABLE_NAME,
                ecoleColums,null,null,null,null,null,null);

        int ecoleIdPos= ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_ID);
        int ecoleNomPos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_NOM);
        int ecoleVillePos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_VILLE);
        int ecoleQuartierPos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_QUARTIER);
        int ecoleTypePos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_TYPE);
        int ecoleCodePos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_CODE);

        DataManager dm = getInstance();
        dm.mEcoles.clear();

        while (ecoleCursor.moveToNext())
        {
            String id = ecoleCursor.getString(ecoleIdPos);
            String nom = ecoleCursor.getString(ecoleNomPos);
            String ville = ecoleCursor.getString(ecoleVillePos);
            String quartier = ecoleCursor.getString(ecoleQuartierPos);
            String type = ecoleCursor.getString(ecoleTypePos);
            String code = ecoleCursor.getString(ecoleCodePos);

            Ecole ecole = new Ecole(id,nom,ville,quartier,type,code);
            mEcoles.add(ecole);
        }

        ecoleCursor.close();
    }

    private void loadClasseFromDatabase()
    {

        final String[] classeColums ={
                DatabaseContract.ClasseEntry.COLUMN_ID,
                DatabaseContract.ClasseEntry.COLUMN_NOM,
                DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE,
        };

        final Cursor classeCursor = DatabaseUtil.mdb.query(DatabaseContract.ClasseEntry.TABLE_NAME,
                classeColums,null,null,null,null,null,null);

        int classeIdPos= classeCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_ID);
        int classeNomPos = classeCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_NOM);
        int ecoleIdPos = classeCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE);


        DataManager dm = getInstance();
        dm.mClasses.clear();

        while (classeCursor.moveToNext())
        {
            String id = classeCursor.getString(classeIdPos);
            String nom = classeCursor.getString(classeNomPos);
            String idEcole = classeCursor.getString(ecoleIdPos);


            Classe classe = new Classe(id,nom,idEcole);
            mClasses.add(classe);
        }

        classeCursor.close();
    }

    private void loadMatiereFromDatabase()
    {

        final String[] matiereColum ={
                DatabaseContract.MatiereEntry.COLUMN_ID,
                DatabaseContract.MatiereEntry.COLUMN_NOM,
        };

        final Cursor matiereCursor = DatabaseUtil.mdb.query(DatabaseContract.MatiereEntry.TABLE_NAME,
                matiereColum,null,null,null,null,null,null);

        int matiereIdPos= matiereCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_ID);
        int matiereNomPos = matiereCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_NOM);



        DataManager dm = getInstance();
        dm.mMatieres.clear();

        while (matiereCursor.moveToNext())
        {
            String id = matiereCursor.getString(matiereIdPos);
            String nom = matiereCursor.getString(matiereNomPos);
            Matiere matiere = new Matiere(id,nom);

            mMatieres.add(matiere);
        }

        matiereCursor.close();
    }

    private void loadEnseignerFromDatabase() {
        final String[] enseignerColums = {
                DatabaseContract.EnseignerEntry.COLUMN_ID_ECOLE,
                DatabaseContract.EnseignerEntry.COLUMN_ID_ENSEIGNANT,
                DatabaseContract.EnseignerEntry.COLUMN_ID_MATIERE,
                DatabaseContract.EnseignerEntry.COLUMN_CLASSE
        };

        final Cursor enseignerCursor = DatabaseUtil.mdb.query(DatabaseContract.EnseignerEntry.TABLE_NAME,
                enseignerColums, null, null, null, null, null, null);

        int enseignerIdEcolePos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_ID_ECOLE);
        int enseignerIdEnseignantPos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_ID_ENSEIGNANT);
        int enseignerIdMatierePos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_ID_MATIERE);
        int enseignerIdClassePos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_CLASSE);


        DataManager dm = getInstance();
        dm.mEnseigners.clear();

        while (enseignerCursor.moveToNext()) {
            String idEcole = enseignerCursor.getString(enseignerIdEcolePos);
            String idEnseignant = enseignerCursor.getString(enseignerIdEnseignantPos);
            String idMatiere = enseignerCursor.getString(enseignerIdMatierePos);
            String idClasse = enseignerCursor.getString(enseignerIdClassePos);

            Enseigner enseigner = new Enseigner(idEnseignant, idMatiere, idEcole, idClasse);
            mEnseigners.add(enseigner);

        }
    }

    public int sizeMatiere()
    {
        return mMatieres.size();
    }




    public int sizeParent()
    {

        loadParentFromDatabase();
        return mParents.size();
    }

    public int sizeEnseignant()
    {
        loadEnseignantFromDatabase();
        return mEnseignants.size();
    }

    public int sizeClasse()
    {
        loadClasseFromDatabase();
        return mClasses.size();
    }

    public List <Ecole> getEcole()
    {
        loadEcoleFromDatabase();
        return mEcoles;
    }

    public List<Matiere> getMatiere()
    {
        loadMatiereFromDatabase();
        return mMatieres;
    }

    public int createNewEcole()
    {
        Ecole ecole = new Ecole("","","","","","");
        mEcoles.add(ecole);
        return mEcoles.size() -1;
    }





    //LE PROFIL COMPLET DE QUELQU'UN
    public Object getProfil(String mail)
    {
        Cursor cp=null;
        Cursor ce=null;
        Object objet=new Object();
        loadParentFromDatabase();
       // loadEnseignantFromDatabase();
//        for(Enseignant enseignant:mEnseignants)
//        {
//            if(enseignant.getMail()==mail)
//                objet= enseignant;
//        }

        for (Parent parent:mParents)
        {
            if(parent.getMail().equals(mail))
                objet= parent;
        }
        return objet;
    }
    public void typeCurrentProfil()
    {
        DataManager dm=DataManager.getInstance();
        String mail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if(dm.getProfil(mail).getClass()== Enseignant.class)
        {
            DatabaseUtil.isEnseignant = true;
            DatabaseUtil.isParent = false;
        }
        else

        if (dm.getProfil(mail).equals(Parent.class))
        {
            DatabaseUtil.isParent=true;
            DatabaseUtil.isEnseignant=false;
        }

    }

    public List<Ecole> getEcoles() {
        loadEcoleFromDatabase();
        return mEcoles;
    }

    public int sizeEcole()
    {
        loadEcoleFromDatabase();
        return mEcoles.size();
    }

    //Retourne les classes d'une école
    public List<Classe> classeFromEcole()
    {
        loadClasseFromDatabase();
        List <Classe> classeSelonEcole = new ArrayList<>();
        classeSelonEcole.clear();

        for(int i=0;i<mClasses.size();i++)
        {
            if(mClasses.get(i).getIdEcole().equals(Current.currentIdEcole))
            {
                classeSelonEcole.add(mClasses.get(i));
            }
        }




        return classeSelonEcole;
    }

    public List <Matiere> getMtiereOfClasseFromEcole()
    {
        List <String> ids = new ArrayList<>();
        List <Matiere> matieres = new ArrayList<>();

        loadEnseignerFromDatabase();
        ids.clear();

        for(int i=0;i<mEnseigners.size();i++)
        {
            //RECUPERER TOUTES LES MATIERE QUI APPARTIENNENT A UNE CLASSE ET ECOLE
            if(mEnseigners.get(i).getIdEcole().equals(Current.currentIdEcole) &&
                    mEnseigners.get(i).getIdClasse().equals(Current.currentIdClasse))
            {
                ids.add(mEnseigners.get(i).getIdMatiere());
            }
        }

        loadMatiereFromDatabase();
        matieres.clear();

        for(int j=0;j<ids.size();j++)
        {
            for (int k=0;k<mMatieres.size();k++)
            {
                if(ids.get(j).equals(mMatieres.get(k).getId()))
                {
                    matieres.add(mMatieres.get(k));
                }
            }
        }
        return matieres;

    }
}
