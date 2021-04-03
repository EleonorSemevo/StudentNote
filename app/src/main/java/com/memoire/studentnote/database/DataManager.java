package com.memoire.studentnote.database;


//import com.google.firebase.auth.FirebaseAuth;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.classes.ClasseT;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Eleve;
import com.memoire.studentnote.classes.Emplois;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.classes.EnseignerT;
import com.memoire.studentnote.classes.Etudier;
import com.memoire.studentnote.classes.Information;
import com.memoire.studentnote.classes.ListeUser;
import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.classes.MesEnfants;
import com.memoire.studentnote.classes.Note;
import com.memoire.studentnote.classes.Parent;
import com.memoire.studentnote.classes.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.PUT;

public class DataManager {
    private static DataManager ourInstance=null;
    private final List<Parent> mParents = new ArrayList<>();
    private final List<Note> mNotes = new ArrayList<>();

    public List<Enseignant> getEnseignants() {
        loadEnseignantFromDatabase();
        return mEnseignants;
    }

    private final List<Enseignant> mEnseignants = new ArrayList<>();
    private final List<Ecole> mEcoles = new ArrayList<>();
    private final List<Classe> mClasses = new ArrayList<>();
    private final List<Matiere> mMatieres = new ArrayList<>();
    private final List<User> mUsers = new ArrayList<>();

    public List<Enseigner> getEnseigners() {
        loadEnseignerFromDatabase();
        return mEnseigners;
    }

    private final List<Enseigner> mEnseigners = new ArrayList<>();
    private final List<ClasseT> mClasseTs = new ArrayList<>();
    private final List<EnseignerT> mEnseignerTs = new ArrayList<>();


    private final List<Emplois> mEmplois = new ArrayList<>();



    private final List <Information> mInformation = new ArrayList<>();
    private final List<ListeUser> mListeUsers = new ArrayList<>();



    ////
    private final List<MesEnfants> mMesEnfants = new ArrayList<>();


    public List<Eleve> getEleves() {
        loadEleveFromDatabase();
        return mEleves;
    }

    private final List<Eleve> mEleves = new ArrayList<>();
    private final List<Etudier> mEtudiers = new ArrayList<>();


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
                DatabaseContract.ParentEntry.COLUMN_NOM,
                DatabaseContract.ParentEntry.COLUMN_PRENOM,
                DatabaseContract.ParentEntry.COLUMN_MAIL,
                DatabaseContract.ParentEntry.COLUMN_MDP,
                DatabaseContract.ParentEntry.COLUMN_TELEPHONE,
                DatabaseContract.ParentEntry._ID
        };

        final android.database.Cursor parentCursor = DatabaseUtil.mdb.query(DatabaseContract.ParentEntry.TABLE_NAME,
                    parentColumns,null,null,null,null,null,null);
        loadFromDatabase((DatabaseOpenHelper) parentCursor);
///
        final String[] noteColums = {
                DatabaseContract.NoteEntry.COLUMN_ID_ECOLE,
                DatabaseContract.NoteEntry.COLUMN_ID_CLASSE,
                DatabaseContract.NoteEntry.COLUMN_ID_MATIERE,
                DatabaseContract.NoteEntry.COLUMN_ID_ELEVE,
                DatabaseContract.NoteEntry.COLUMN_TYPE,
                DatabaseContract.NoteEntry.COLUMN_DATE_COMPOSITION,
                DatabaseContract.NoteEntry.COLUMN_DESCRIPTION,
                DatabaseContract.NoteEntry._ID
        };

        final android.database.Cursor noteCursor = DatabaseUtil.mdb.query(DatabaseContract.NoteEntry.TABLE_NAME,
                noteColums,null,null,null,null,null,null);

        loadFromDatabase((DatabaseOpenHelper) noteCursor);
///

        final String[] enseignantColumns = new String[]{
                DatabaseContract.EnseignantEntry.COLUMN_NOM,
                DatabaseContract.EnseignantEntry.COLUMN_PRENOM,
                DatabaseContract.EnseignantEntry.COLUMN_MAIL,
                DatabaseContract.EnseignantEntry.COLUMN_MDP,
                DatabaseContract.EnseignantEntry.COLUMN_TELEPHONE,
                DatabaseContract.EnseignantEntry._ID

        };

        final android.database.Cursor enseignantCursor = DatabaseUtil.mdb.query(DatabaseContract.ParentEntry.TABLE_NAME,
                enseignantColumns,null,null,null,null,null,null);
        loadFromDatabase((DatabaseOpenHelper) enseignantCursor);


    }

    private void loadParentFromDatabase()
    {
        final String[] parentColumns ={
                DatabaseContract.ParentEntry.COLUMN_NOM,
                DatabaseContract.ParentEntry.COLUMN_PRENOM,
                DatabaseContract.ParentEntry.COLUMN_MAIL,
                DatabaseContract.ParentEntry.COLUMN_MDP,
                DatabaseContract.ParentEntry.COLUMN_TELEPHONE,
                DatabaseContract.ParentEntry._ID
        };

        final android.database.Cursor parentCursor = DatabaseUtil.mdb.query(DatabaseContract.ParentEntry.TABLE_NAME,
                parentColumns,null,null,null,null,null,null);

        int parentNomPos = parentCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_NOM);
        int parentPrenomPos = parentCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_PRENOM);
        int parentMailPos = parentCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_MAIL);
        int parentMdpPos = parentCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_MDP);
        int parentTelephone = parentCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_TELEPHONE);
        int parentId = parentCursor.getColumnIndex(DatabaseContract.ParentEntry._ID);


        DataManager dm =getInstance();
        dm.mParents.clear();
        while (parentCursor.moveToNext())
        {
            String nom = parentCursor.getString(parentNomPos);
            String prenom = parentCursor.getString(parentPrenomPos);
            String mail = parentCursor.getString(parentMailPos);
            String mdp = parentCursor.getString(parentMdpPos);
            String telephone = parentCursor.getString(parentTelephone);
            int id = parentCursor.getInt(parentId);

            Parent parent = new Parent(id,nom,prenom,mail,mdp,telephone);
            dm.mParents.add(parent);
        }

        parentCursor.close();
    }

    private void loadEnseignantFromDatabase()
    {
        final String[] enseignantColumns ={
                DatabaseContract.EnseignantEntry.COLUMN_NOM,
                DatabaseContract.EnseignantEntry.COLUMN_PRENOM,
                DatabaseContract.EnseignantEntry.COLUMN_MAIL,
                DatabaseContract.EnseignantEntry.COLUMN_MDP,
                DatabaseContract.EnseignantEntry.COLUMN_TELEPHONE,
                DatabaseContract.EnseignantEntry._ID

        };

        final android.database.Cursor enseignantCursor = DatabaseUtil.mdb.query(DatabaseContract.EnseignantEntry.TABLE_NAME,
                enseignantColumns,null,null,null,null,null,null);
        //loadFromDatabase((DatabaseOpenHelper) enseignantCursor);

        int enseignantNomPos = enseignantCursor.getColumnIndex(DatabaseContract.EnseignantEntry.COLUMN_NOM);
        int enseignantPrenomPos = enseignantCursor.getColumnIndex(DatabaseContract.EnseignantEntry.COLUMN_PRENOM);
        int enseignantMailPos = enseignantCursor.getColumnIndex(DatabaseContract.EnseignantEntry.COLUMN_MAIL);
        int enseignantMdpPos = enseignantCursor.getColumnIndex(DatabaseContract.EnseignantEntry.COLUMN_MDP);
        int enseignantTelephone = enseignantCursor.getColumnIndex(DatabaseContract.EnseignantEntry.COLUMN_TELEPHONE);
        int enseignantId = enseignantCursor.getColumnIndex(DatabaseContract.EnseignantEntry._ID);

        DataManager dm =getInstance();
        dm.mEnseignants.clear();
        while (enseignantCursor.moveToNext())
        {
            String nom = enseignantCursor.getString(enseignantNomPos);
            String prenom = enseignantCursor.getString(enseignantPrenomPos);
            String mail = enseignantCursor.getString(enseignantMailPos);
            String mdp = enseignantCursor.getString(enseignantMdpPos);
            String telephone = enseignantCursor.getString(enseignantTelephone);
            int id = enseignantCursor.getInt(enseignantId);

            Enseignant enseignant = new Enseignant(id,nom,prenom,mail,mdp,telephone);
            dm.mEnseignants.add(enseignant);
        }

        enseignantCursor.close();
    }

    private void loadEcoleFromDatabase()
    {
        final String[] ecoleColums ={
                DatabaseContract.EnseignantEntry._ID,
                DatabaseContract.EcoleEntry.COLUMN_NOM,
                DatabaseContract.EcoleEntry.COLUMN_VILLE,
                DatabaseContract.EcoleEntry.COLUMN_QUARTIER,
                DatabaseContract.EcoleEntry.COLUMN_TYPE,
                DatabaseContract.EcoleEntry.COLUMN_CODE

        };

        final android.database.Cursor ecoleCursor = DatabaseUtil.mdb.query(DatabaseContract.EcoleEntry.TABLE_NAME,
                ecoleColums,null,null,null,null,null,null);

        int ecoleIdPos= ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry._ID);
        int ecoleNomPos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_NOM);
        int ecoleVillePos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_VILLE);
        int ecoleQuartierPos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_QUARTIER);
        int ecoleTypePos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_TYPE);
        int ecoleCodePos = ecoleCursor.getColumnIndex(DatabaseContract.EcoleEntry.COLUMN_CODE);

        DataManager dm = getInstance();
        dm.mEcoles.clear();

        while (ecoleCursor.moveToNext())
        {
            int id = ecoleCursor.getInt(ecoleIdPos);
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
                DatabaseContract.ClasseEntry._ID,
                DatabaseContract.ClasseEntry.COLUMN_NOM,
                DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE,
        };

        final android.database.Cursor classeCursor = DatabaseUtil.mdb.query(DatabaseContract.ClasseEntry.TABLE_NAME,
                classeColums,null,null,null,null,null,null);

        int classeIdPos= classeCursor.getColumnIndex(DatabaseContract.ClasseEntry._ID);
        int classeNomPos = classeCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_NOM);
        int ecoleIdPos = classeCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE);


        DataManager dm = getInstance();
        dm.mClasses.clear();

        while (classeCursor.moveToNext())
        {
            int id = classeCursor.getInt(classeIdPos);
            String nom = classeCursor.getString(classeNomPos);
            int idEcole = classeCursor.getInt(ecoleIdPos);


            Classe classe = new Classe(id,nom,idEcole);
            mClasses.add(classe);
        }

        classeCursor.close();
    }

    private void loadClasseTFromDatabase()
    {

        final String[] classeTColums ={
                DatabaseContract.ClasseEntry._ID,
                DatabaseContract.ClasseEntry.COLUMN_NOM,
                DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE,
        };

        final android.database.Cursor classeCursor = DatabaseUtil.mdb.query(DatabaseContract.ClasseEntry.TABLE_NAME,
                classeTColums,null,null,null,null,null,null);

        int classeIdPos= classeCursor.getColumnIndex(DatabaseContract.ClasseEntry._ID);
        int classeNomPos = classeCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_NOM);
        int ecoleIdPos = classeCursor.getColumnIndex(DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE);


        DataManager dm = getInstance();
        dm.mClasseTs.clear();

        while (classeCursor.moveToNext())
        {
            int id = classeCursor.getInt(classeIdPos);
            String nom = classeCursor.getString(classeNomPos);
            int idEcole = classeCursor.getInt(ecoleIdPos);
            ClasseT classe = new ClasseT(id,nom,dm.getEcoleSelonId(idEcole));
            mClasseTs.add(classe);
        }

        classeCursor.close();

    }

    public List<EnseignerT> getEnseignerTs() {
        loadEnseignerTFromDatabase();
        return mEnseignerTs;
    }

    public void loadEnseignerTFromDatabase()
    {

        loadEnseignerFromDatabase();
        loadClasseFromDatabase();
        loadMatiereFromDatabase();
        loadEnseignantFromDatabase();

        DataManager dm=getInstance();

        mEnseignerTs.clear();
        for (int i=0;i<mEnseigners.size();i++)
        {
            //List<Enseigner> m =mEnseigners;

            Enseigner enseigner= mEnseigners.get(i);
            Ecole ecole= dm.getEcoleSelonId(enseigner.getIdEcole());
            Classe classe =  dm.getClasseSelonId(enseigner.getIdClasse());
            Matiere matiere = dm.getMatiereSelonId(enseigner.getIdMatiere());
            Enseignant enseignant =  dm.getEnseignantSelonId(enseigner.getIdEnseignant());

            mEnseignerTs.add(new EnseignerT(ecole, classe, matiere, enseignant));
        }

//        List<EnseignerT> m =new ArrayList<>();
//        m = mEnseignerTs;
    }

    private void loadMatiereFromDatabase()
    {

        final String[] matiereColum ={
                DatabaseContract.MatiereEntry._ID,
                DatabaseContract.MatiereEntry.COLUMN_NOM,
        };

        final android.database.Cursor matiereCursor = DatabaseUtil.mdb.query(DatabaseContract.MatiereEntry.TABLE_NAME,
                matiereColum,null,null,null,null,null,null);

        int matiereIdPos= matiereCursor.getColumnIndex(DatabaseContract.MatiereEntry._ID);
        int matiereNomPos = matiereCursor.getColumnIndex(DatabaseContract.MatiereEntry.COLUMN_NOM);



        DataManager dm = getInstance();
        dm.mMatieres.clear();

        while (matiereCursor.moveToNext())
        {
            int id = matiereCursor.getInt(matiereIdPos);
            String nom = matiereCursor.getString(matiereNomPos);
            Matiere matiere = new Matiere(id,nom);

            mMatieres.add(matiere);
        }

        matiereCursor.close();
    }

    private void loadInformationsFromDatabase()
    {
        final String[] informationsColumns ={
                DatabaseContract.InformationEntry.COLUMN_DESCRIPTION,
                DatabaseContract.InformationEntry.COLUMN_CHEMIN,
                DatabaseContract.InformationEntry.COLUMN_ID_ECOLE,
                DatabaseContract.InformationEntry.COLUMN_ID_AUTEUR,
                DatabaseContract.InformationEntry.COLUMN_DATE_PUBLICATION,
                DatabaseContract.InformationEntry._ID
        };

        final android.database.Cursor informationCursor = DatabaseUtil.mdb.query(DatabaseContract.InformationEntry.TABLE_NAME,
                informationsColumns,null,null,null,null,null,null);

        int infosDatePos = informationCursor.getColumnIndex(DatabaseContract.InformationEntry.COLUMN_DATE_PUBLICATION);
        int infosCheminPos = informationCursor.getColumnIndex(DatabaseContract.InformationEntry.COLUMN_CHEMIN);
        int infosidEcolePos = informationCursor.getColumnIndex(DatabaseContract.InformationEntry.COLUMN_ID_ECOLE);
        int infosIdAuteurPos = informationCursor.getColumnIndex(DatabaseContract.InformationEntry.COLUMN_ID_AUTEUR);
        int infosDescriptionPos = informationCursor.getColumnIndex(DatabaseContract.InformationEntry.COLUMN_DESCRIPTION);
        int infosId = informationCursor.getColumnIndex(DatabaseContract.InformationEntry._ID);


        DataManager dm =getInstance();
        dm.mInformation.clear();
        while (informationCursor.moveToNext())
        {
            String datePublication = informationCursor.getString(infosDatePos);
            String chemin = informationCursor.getString(infosCheminPos);
            String description = informationCursor.getString(infosDescriptionPos);
            int idEcole = informationCursor.getInt(infosidEcolePos);
            int idAuteur = informationCursor.getInt(infosIdAuteurPos);
            int id = informationCursor.getInt(infosId);

            Information information = new Information(id,description,datePublication,idEcole,idAuteur);
            dm.mInformation.add(information);
        }

        informationCursor.close();
    }

    private void loadEnseignerFromDatabase() {
        final String[] enseignerColums = {
                DatabaseContract.EnseignerEntry._ID,
                DatabaseContract.EnseignerEntry.COLUMN_ID_ECOLE,
                DatabaseContract.EnseignerEntry.COLUMN_ID_ENSEIGNANT,
                DatabaseContract.EnseignerEntry.COLUMN_ID_MATIERE,
                DatabaseContract.EnseignerEntry.COLUMN_ID_CLASSE
        };

        final android.database.Cursor enseignerCursor = DatabaseUtil.mdb.query(DatabaseContract.EnseignerEntry.TABLE_NAME,
                enseignerColums, null, null, null, null, null, null);

        int enseignerIdEcolePos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_ID_ECOLE);
        int enseignerIdEnseignantPos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_ID_ENSEIGNANT);
        int enseignerIdMatierePos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_ID_MATIERE);
        int enseignerIdClassePos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry.COLUMN_ID_CLASSE);
        int enseignerIdEnseignerPos = enseignerCursor.getColumnIndex(DatabaseContract.EnseignerEntry._ID);


        DataManager dm = getInstance();
        dm.mEnseigners.clear();

        while (enseignerCursor.moveToNext()) {
            int idEcole = enseignerCursor.getInt(enseignerIdEcolePos);
            int idEnseignant = enseignerCursor.getInt(enseignerIdEnseignantPos);
            int idMatiere = enseignerCursor.getInt(enseignerIdMatierePos);
            int idClasse = enseignerCursor.getInt(enseignerIdClassePos);
            int idEnseigner = enseignerCursor.getInt(enseignerIdEnseignerPos);

            Enseigner enseigner = new Enseigner(idEnseigner,idEnseignant, idMatiere, idEcole, idClasse);
            mEnseigners.add(enseigner);

        }

        enseignerCursor.close();
    }

    private void loadListeUsersFromDatabase()
    {
        final String[] listusersColumns = {
                DatabaseContract.ListUserEntry.COLUMN_UID,
                DatabaseContract.ListUserEntry.COLUMN_NOM,
                DatabaseContract.ListUserEntry.COLUMN_PRENOM,
                DatabaseContract.ListUserEntry.COLUMN_TYPE,
                DatabaseContract.ListUserEntry.COLUMN_MAIL,
                DatabaseContract.ListUserEntry._ID
        };
        final Cursor liste_userCursor = DatabaseUtil.mdb.query(DatabaseContract.ListUserEntry.TABLE_NAME,
                listusersColumns, null, null, null, null,null,null);
        int uidPos =liste_userCursor.getColumnIndex(DatabaseContract.ListUserEntry.COLUMN_UID);
        int mailPos= liste_userCursor.getColumnIndex(DatabaseContract.ListUserEntry.COLUMN_MAIL);
        int nomPos = liste_userCursor.getColumnIndex(DatabaseContract.ListUserEntry.COLUMN_NOM);
        int prenomPos = liste_userCursor.getColumnIndex(DatabaseContract.ListUserEntry.COLUMN_PRENOM);
        int typePos= liste_userCursor.getColumnIndex(DatabaseContract.ListUserEntry.COLUMN_TYPE);
        int idPos = liste_userCursor.getColumnIndex(DatabaseContract.ListUserEntry._ID);

        DataManager dm = DataManager.getInstance();
        dm.mListeUsers.clear();
        while(liste_userCursor.moveToNext()){
            String uid = liste_userCursor.getString(uidPos);
            String mail = liste_userCursor.getString(mailPos);
            String nom = liste_userCursor.getString(nomPos);
            String prenom = liste_userCursor.getString(prenomPos);
            String type = liste_userCursor.getString(typePos);
            int id = liste_userCursor.getInt(idPos);

            ListeUser listeUser = new ListeUser(id,uid,nom,prenom,mail,type);
            mListeUsers.add(listeUser);
        }
        liste_userCursor.close();
    }

    public int sizeMatiere()
    {
        loadMatiereFromDatabase();
        return mMatieres.size();
    }

    private void loadEleveFromDatabase()
    {
        final String[] eleveColums ={
                DatabaseContract.EleveEntry._ID,
                DatabaseContract.EleveEntry.COLUMN_NOM,
                DatabaseContract.EleveEntry.COLUMN_PRENOM,
               // DatabaseContract.EleveEntry.COLUMN_MATRICULE,
                DatabaseContract.EleveEntry.COLUMN_SEXE,
                DatabaseContract.EleveEntry.COLUMN_DATE_NAISSASSNCE

        };

        final android.database.Cursor eleveCursor = DatabaseUtil.mdb.query(DatabaseContract.EleveEntry.TABLE_NAME,
                eleveColums,null,null,null,null,null,null);

        int eleveIdPos= eleveCursor.getColumnIndex(DatabaseContract.EleveEntry._ID);
        int eleveNomPos = eleveCursor.getColumnIndex(DatabaseContract.EleveEntry.COLUMN_NOM);
   //     int eleveMatriculePos = eleveCursor.getColumnIndex(DatabaseContract.EleveEntry.COLUMN_MATRICULE);
        int eleveSexPos = eleveCursor.getColumnIndex(DatabaseContract.EleveEntry.COLUMN_SEXE);
        int eleveDateNaissancePos = eleveCursor.getColumnIndex(DatabaseContract.EleveEntry.COLUMN_DATE_NAISSASSNCE);
        int elevePrenomPos = eleveCursor.getColumnIndex(DatabaseContract.EleveEntry.COLUMN_PRENOM);

        DataManager dm = getInstance();
        dm.mEleves.clear();

        while (eleveCursor.moveToNext())
        {
            int id = eleveCursor.getInt(eleveIdPos);
            String nom = eleveCursor.getString(eleveNomPos);
            String prenom = eleveCursor.getString(elevePrenomPos);
            String sexe = eleveCursor.getString(eleveSexPos);
     //       String matricule = eleveCursor.getString(eleveMatriculePos);
            String dateNaissaance = eleveCursor.getString(eleveDateNaissancePos);

            Eleve eleve = new Eleve(id,nom,prenom,sexe,dateNaissaance);
            mEleves.add(eleve);
        }

        eleveCursor.close();
    }

    public void chercherEnfant()
    {

    }

    public List<Etudier> getEtudiers()
    {
        loadAllEtudiers();
        return mEtudiers;
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

    public java.util.List<Ecole> getEcole()
    {
        loadEcoleFromDatabase();
        return mEcoles;
    }

    public java.util.List<Matiere> getMatiere()
    {
        loadMatiereFromDatabase();
        return mMatieres;
    }



    //LE PROFIL COMPLET DE QUELQU'UN
    public Object getProfil(String mail)
    {
        android.database.Cursor cp=null;
        android.database.Cursor ce=null;
        Object objet=new Object();
        loadParentFromDatabase();

        for (Parent parent:mParents)
        {
            if(parent.getMail().equals(mail))
                objet= parent;
        }
        return objet;
    }


    public java.util.List<Ecole> getEcoles() {
        loadEcoleFromDatabase();
        return mEcoles;
    }

    public int sizeEcole()
    {
        loadEcoleFromDatabase();
        return mEcoles.size();
    }

    //Retourne les classes d'une école
    public java.util.List<Classe> classeFromEcole()
    {
        loadClasseFromDatabase();
        java.util.List<Classe> classeSelonEcole = new ArrayList<>();
        classeSelonEcole.clear();

        for(int i=0;i<mClasses.size();i++)
        {
            if(mClasses.get(i).getIdEcole() == (Current.currentIdEcole))
            {
                classeSelonEcole.add(mClasses.get(i));
            }
        }




        return classeSelonEcole;
    }

    ///Retourne les matieres d'une classe selon une école


    public java.util.List<Matiere> getMtiereOfClasseFromEcole()
    {
        loadEnseignerTFromDatabase();
        java.util.List<Matiere> matieres =new ArrayList<>();

        for(EnseignerT enseignerT:mEnseignerTs)
        {
            int idClass =Current.currentIdClasse;
            int idEcole = Current.currentIdEcole;
            if(enseignerT.getEcole().getId()==(idEcole) && enseignerT.getClasse().getId()==(idClass))
                matieres.add(enseignerT.getMatiere());
        }

        return matieres;

    }



    ///LES CLASSES SELON ID

    private Ecole getEcoleSelonId(int id)
    {
        loadEcoleFromDatabase();
        loadClasseFromDatabase();
        Ecole ecole=null;
        for(Ecole ec:mEcoles)
        {
            if(id==ec.getId())
            {

                return ec;
            }
        }
        return null;
    }

    private Classe getClasseSelonId(int id)
    {
        loadClasseFromDatabase();
        for (Classe classe:mClasses)
        {
            if(id==classe.getId())
                return classe;
        }
        return null;
    }

    private Matiere getMatiereSelonId(int id)
    {
        loadMatiereFromDatabase();
        for (Matiere matiere:mMatieres)
        {
            if(id==matiere.getId())
                return matiere;
        }
        return null;
    }
    private Enseignant getEnseignantSelonId(int id)
    {
        loadEnseignantFromDatabase();
        for(Enseignant enseignant:mEnseignants)
        {
            if(id==enseignant.getId())
                return enseignant;
        }
        return null;
    }

//////////////////////////
    private void loadMesEnfantsFromDatabase()
    {
        final String[] mesEnfantsColumns ={
                DatabaseContract.MesEnfantsEntry._ID,
                DatabaseContract.MesEnfantsEntry.COLUMN_ID_ELEVE,
                DatabaseContract.MesEnfantsEntry.COLUMN_NOM,
                DatabaseContract.MesEnfantsEntry.COLUMN_PRENOM,
                DatabaseContract.MesEnfantsEntry.COLUMN_ID_ECOLE,
                DatabaseContract.MesEnfantsEntry.COLUMN_NOM_ECOLE,
                DatabaseContract.MesEnfantsEntry.COLUMN_ID_PARENT,
                DatabaseContract.MesEnfantsEntry.COLUMN_ID_CLASSE,
                DatabaseContract.MesEnfantsEntry.COLUMN_CLASSE,
                DatabaseContract.MesEnfantsEntry._ID
        };

        final android.database.Cursor mesEnfantsCursor = DatabaseUtil.mdb.query(DatabaseContract.MesEnfantsEntry.TABLE_NAME,
                mesEnfantsColumns,null,null,null,null,null,null);


        int mesEnfantsIdElevePos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_ID_ELEVE);
        int mesEnfantsNomPos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_NOM);
        int mesEnfantsPrenomPos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_PRENOM);

        int mesEnfantsIdEcolePos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_ID_ECOLE);
        int mesEnfantsNomEcolePos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_NOM_ECOLE);
        int mesEnfantsClassePos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_CLASSE);

        int mesEnfantsIdParentPos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_ID_PARENT);
        int mesEnfantsIdClassPos = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry.COLUMN_ID_CLASSE);

        int mesEnfantsId = mesEnfantsCursor.getColumnIndex(DatabaseContract.MesEnfantsEntry._ID);

        DataManager dm =getInstance();
        dm.mMesEnfants.clear();
        while (mesEnfantsCursor.moveToNext())
        {
            int idEleve = mesEnfantsCursor.getInt(mesEnfantsIdElevePos);
            String nom = mesEnfantsCursor.getString(mesEnfantsNomPos);
            String prenom = mesEnfantsCursor.getString(mesEnfantsPrenomPos);
            int idEcole = mesEnfantsCursor.getInt(mesEnfantsIdEcolePos);
            String nomEcole = mesEnfantsCursor.getString(mesEnfantsNomEcolePos);
            String classe = mesEnfantsCursor.getString(mesEnfantsClassePos);
            int idParent = mesEnfantsCursor.getInt(mesEnfantsIdParentPos);
            int idClasse = mesEnfantsCursor.getInt(mesEnfantsIdClassPos);


            int id = mesEnfantsCursor.getInt(mesEnfantsId);

            MesEnfants mesEnfants = new MesEnfants(id,idParent,idEleve,nom,prenom,idEcole, nomEcole,idClasse,classe);
            dm.mMesEnfants.add(mesEnfants);
        }

        mesEnfantsCursor.close();
    }
    private void loadUsersFromDatabase()
    {


        final String[] usersColumns ={
                DatabaseContract.UserEntry.COLUMN_MAIL,
                DatabaseContract.UserEntry.COLUMN_NOM,
                DatabaseContract.UserEntry.COLUMN_PRENOM,
                DatabaseContract.UserEntry.COLUMN_TELEPHONE,
                DatabaseContract.UserEntry.COLUMN_TYPE,
                DatabaseContract.UserEntry.COLUMN_MOTS_DE_PASSE,
                DatabaseContract.UserEntry._ID
        };

        final android.database.Cursor userCursor = DatabaseUtil.mdb.query(DatabaseContract.UserEntry.TABLE_NAME,
                usersColumns,null,null,null,null,null,null);

        int idPos = userCursor.getColumnIndex(DatabaseContract.UserEntry._ID);
        int nomPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_NOM);
        int prenomPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_PRENOM);
        int mailPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_MAIL);
        int typePos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_TYPE);
        int mdpPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_MOTS_DE_PASSE);
        int telephonePos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_TELEPHONE);

        DataManager dm =getInstance();
        dm.mUsers.clear();
        while (userCursor.moveToNext())
        {
            int id = userCursor.getInt(idPos);
            String mail = userCursor.getString(mailPos);
            String nom = userCursor.getString(nomPos);
            String prenom  = userCursor.getString(prenomPos);
            String telephone =userCursor.getString(telephonePos);
            String type =userCursor.getString(typePos);
            String mdp =userCursor.getString(mdpPos);

            User user = new User(id,nom,prenom,mail,mdp,telephone,type);
            dm.mUsers.add(user);

        }

        userCursor.close();
    }

    public User getUserSelonMail(String mail)
    {
        loadUsersFromDatabase();
        for(int f=0;f<mUsers.size();f++)
        {
            if(mUsers.get(f).getMail().equals(mail))
                return mUsers.get(f);
        }
        return new User();
    }

    public void loadAllEtudiers()
    {


        final String[] etudierColumns ={
                DatabaseContract.EtudierEntry.COLUMN_ID_ELEVE,
                DatabaseContract.EtudierEntry.COLUMN_ID_ECOLE,
                DatabaseContract.EtudierEntry.COLUMN_ID_CLASSE,
                DatabaseContract.EtudierEntry.COLUMN_ANNEE,
                DatabaseContract.EtudierEntry.COLUMN_MATRICULE,
                DatabaseContract.EtudierEntry._ID
        };

        final android.database.Cursor etudierCursor = DatabaseUtil.mdb.query(DatabaseContract.EtudierEntry.TABLE_NAME,
                etudierColumns,null,null,null,null,null,null);

        int idEcolePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ID_ECOLE);
        int idClassePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ID_CLASSE);
        int idElevePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ID_ELEVE);
        int anneePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ANNEE);
        int idPos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry._ID);
        int matriculePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_MATRICULE);

        DataManager dm =getInstance();
        dm.mEtudiers.clear();
        while (etudierCursor.moveToNext())
        {
            int idEcole = etudierCursor.getInt(idEcolePos);
            int idClasse = etudierCursor.getInt(idClassePos);
            int idEleve = etudierCursor.getInt(idElevePos);
            int annee  = etudierCursor.getInt(anneePos);
            int id =etudierCursor.getInt(idPos);
            String matricule = etudierCursor.getString(matriculePos);

            Etudier etudier = new Etudier(id,idEleve,idClasse,idEcole,annee,matricule);
            dm.mEtudiers.add(etudier);

        }

        etudierCursor.close();
    }

    private void loadAllNotes()
    {


        final String[] notesColumns ={
                DatabaseContract.NoteEntry.COLUMN_ID_ELEVE,
                DatabaseContract.NoteEntry.COLUMN_ID_ECOLE,
                DatabaseContract.NoteEntry.COLUMN_ID_CLASSE,
                DatabaseContract.NoteEntry.COLUMN_ANNEE_SCOLAIRE,

                DatabaseContract.NoteEntry.COLUMN_DATE_COMPOSITION,
                DatabaseContract.NoteEntry.COLUMN_DESCRIPTION,
                DatabaseContract.NoteEntry.COLUMN_ID_MATIERE,
                DatabaseContract.NoteEntry.COLUMN_TYPE,

                DatabaseContract.NoteEntry.COLUMN_NOTE,

                DatabaseContract.NoteEntry._ID
        };

        final android.database.Cursor noteCursor = DatabaseUtil.mdb.query(DatabaseContract.NoteEntry.TABLE_NAME,
                notesColumns,null,null,null,null,null,null);

        int idEcolePos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_ID_ECOLE);
        int idClassePos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_ID_CLASSE);
        int idElevePos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_ID_ELEVE);
        int idMatierePos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_ID_MATIERE);

        int typePos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_TYPE);
        int descriptionPos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_DESCRIPTION);
        int notePos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_NOTE);
        int dateCompPos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_DATE_COMPOSITION);
        int anneScolairePos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry.COLUMN_ANNEE_SCOLAIRE);


        int idPos = noteCursor.getColumnIndex(DatabaseContract.NoteEntry._ID);

        DataManager dm =getInstance();
        dm.mNotes.clear();
        while (noteCursor.moveToNext())
        {
            int idEcole = noteCursor.getInt(idEcolePos);
            int idClasse = noteCursor.getInt(idClassePos);
            int idEleve = noteCursor.getInt(idElevePos);
            int idMatiere = noteCursor.getInt(idMatierePos);

            String type = noteCursor.getString(typePos);
            String description = noteCursor.getString(descriptionPos);
            double notes = noteCursor.getDouble(notePos);
            String dateComp = noteCursor.getString(dateCompPos);
            int anneScolaire = noteCursor.getInt(anneScolairePos);

            int id =noteCursor.getInt(idPos);

            Note note =new Note(id,idMatiere,idEleve,idClasse,idEcole,type,dateComp,description,anneScolaire,notes);
            dm.mNotes.add(note);

        }

        noteCursor.close();
    }

    public List getNotes()
    {
        loadAllNotes();
        return mNotes;
    }

    public boolean virifierEtudier(int idEcole, int idEleve)
    {
        loadAllEtudiers();

        Etudier l_etudier = null;

        for(Etudier etudier:mEtudiers)
        {
            if(etudier.getIdEcole()==idEcole && etudier.getIdEleve() == idEleve)
            {
                l_etudier = etudier;
            }
        }

        if (l_etudier==null)
            return false;
        else
            return true;
    }


    public int eleveSize()
    {
        loadEleveFromDatabase();
        return mEleves.size();
    }

    public List<MesEnfants> getMesEnfants() {
        loadMesEnfantsFromDatabase();
        return mMesEnfants;
    }

    public int getParentFromMail(String mail)
    {
        loadParentFromDatabase();
        int id=0;
        for(int g=0;g<mParents.size();g++)
        {
            if(mParents.get(g).getMail().equals(mail))
                id=mParents.get(g).getId();
        }
        return id;
    }

    public int getIdClasseFromEtudier(int idEleve,int annee)
    {
        loadAllEtudiers();
        int idClasse=0;
        for(int f=0;f<mEtudiers.size();f++)
        {
            if(mEtudiers.get(f).getIdEleve()==idEleve && mEtudiers.get(f).getAnnee()==annee)
                idClasse = mEtudiers.get(f).getIdClasse();
        }
        return idClasse;
    }

    public String getNomClasseFromClasse(int idClasse)
    {
        loadClasseFromDatabase();
        String nomClasse = null;
        for(int h=0;h<mClasses.size();h++)
        {
            if(mClasses.get(h).getId()==idClasse)
                nomClasse = mClasses.get(h).getNom();
        }
        return nomClasse;
    }

    public String getNomEcoleFromEcole(int idEcole)
    {
        String nomEcole = null;
        loadEcoleFromDatabase();
        for(int d=0;d<mEcoles.size();d++)
        {
            if(mEcoles.get(d).getId()==idEcole)
                nomEcole = mEcoles.get(d).getNom();
        }
        return nomEcole;
    }

    public int getIdElveFromMatricule(int idEcole, String matricule)
    {
        loadAllEtudiers();
        int id=0;
        for(int i=0;i<mEtudiers.size();i++)
        {
            Etudier etudier=mEtudiers.get(i);
            if(etudier.getMatricule().equals(matricule) && etudier.getIdEleve()==idEcole)
            {
                id=etudier.getIdEleve();
            }
        }
        return id;

    }

    public List<Emplois> getEmplois() {
        loadAllEmplois();
        return mEmplois;
    }

    private void loadAllEmplois(){
        final String[] emploisColumns= {
                DatabaseContract.EmploisEntry.COLUMN_ID_ECOLE,
                DatabaseContract.EmploisEntry.COLUMN_ID_CLASSE,
                DatabaseContract.EmploisEntry.COLUMN_ID_MATIERE,
                DatabaseContract.EmploisEntry.COLUMN_ANNEE_SCOLAIRE,
                DatabaseContract.EmploisEntry.COLUMN_JOUR,
                DatabaseContract.EmploisEntry.COLUMN_HEURE_DEBUT,
                DatabaseContract.EmploisEntry.COLUMN_HEURE_FIN,
                DatabaseContract.EmploisEntry._ID
        };

        final Cursor emploisCursor = DatabaseUtil.mdb.query(
                DatabaseContract.EmploisEntry.TABLE_NAME,
                emploisColumns, null, null,null,
                null, DatabaseContract.EmploisEntry.COLUMN_HEURE_DEBUT+" ASC");
        int idEcolePos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry.COLUMN_ID_ECOLE);
        int idClassePos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry.COLUMN_ID_CLASSE);
        int idMatierePos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry.COLUMN_ID_MATIERE);
        int anneScolairePos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry.COLUMN_ANNEE_SCOLAIRE);
        int jourPos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry.COLUMN_JOUR);
        int heure_debutPos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry.COLUMN_HEURE_DEBUT);
        int heure_finPos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry.COLUMN_HEURE_FIN);
        int idPos = emploisCursor.getColumnIndex(DatabaseContract.EmploisEntry._ID);

        DataManager dm = getInstance();
        dm.mEmplois.clear();

        while (emploisCursor.moveToNext())
        {
            int idEcole = emploisCursor.getInt(idEcolePos);
            int idClasse = emploisCursor.getInt(idClassePos);
            int idMatiere = emploisCursor.getInt(idMatierePos);
            int annee = emploisCursor.getInt(anneScolairePos);
            String jour = emploisCursor.getString(jourPos);
            int debut = emploisCursor.getInt(heure_debutPos);
            int fin = emploisCursor.getInt(heure_finPos);
            int id = emploisCursor.getInt(idPos);

            Emplois emplois = new Emplois(id,idEcole,idClasse,idMatiere,jour, debut,fin,annee);
            mEmplois.add(emplois);
        }

        emploisCursor.close();

    }


    public String getNomEleve(int idEleve)
    {
        loadEleveFromDatabase();
        String nom =null;
        for(int ab=0;ab<mEleves.size();ab++)
        {
            if(mEleves.get(ab).getId()==idEleve)
                nom = mEleves.get(ab).getNom();
        }
        return nom;
    }
    public String getPrenomEleve(int idEleve)
    {
        loadEleveFromDatabase();
        String prenom =null;
        for(int abc=0;abc<mEleves.size();abc++)
        {
            if(mEleves.get(abc).getId()==idEleve)
                prenom = mEleves.get(abc).getPrenom();
        }
        return prenom;
    }

    public String nomClasseFromClass(int idClasse){
        String nom=null;
        loadClasseFromDatabase();
        for(int q=0;q<mClasses.size();q++)
        {
            if(mClasses.get(q).getId()==idClasse)
                nom = mClasses.get(q).getNom();
        }
        return  nom;
    }

    public List<Information> getInformation() {
        loadInformationsFromDatabase();
        return mInformation;
    }

    public List<ListeUser> getListeUsers()
    {
        loadListeUsersFromDatabase();
        return mListeUsers;
    }

    public Matiere getMatiere(int id)
    {
        loadMatiereFromDatabase();
        Matiere matiere=new Matiere();
        for(int d=0;d<mMatieres.size();d++)
        {
            if (mMatieres.get(d).getId()==id)
                matiere = mMatieres.get(d);
        }
        return matiere;
    }

    public List<Classe> getClasses()
    {
        loadClasseFromDatabase();
        return mClasses;
    }

    public Classe getClasse(int idClasse)
    {
        loadClasseFromDatabase();

        for(int f=0;f<mClasses.size();f++)
        {
            if (mClasses.get(f).getId()==idClasse)
                return mClasses.get(f);
        }
        return new Classe();
    }

    public Enseignant getEnseignantDepuisMail(String mail)
    {
        loadEnseignantFromDatabase();
        for(int j=0;j<mEnseignants.size();j++)
        {
            if(mEnseignants.get(j).getMail().equals(mail))
                return mEnseignants.get(j);
        }
        return new Enseignant();
    }

    public List<Ecole> getListeEcoleEnseignant(int idEnseignant)
    {
        loadEnseignerFromDatabase();
        List<Ecole> ecoles = new ArrayList<>();
        loadEcoleFromDatabase();

        for (Enseigner enseigner: mEnseigners) {
            if(enseigner.getIdEnseignant()==idEnseignant)
                ecoles.add(getEcole(enseigner.getIdEcole()));
        }
    //Enlever les redondances
        List<Ecole> ec =new ArrayList<>();
        for(int k=0;k<ecoles.size();k++)
        {
            if(!ec.contains(ecoles.get(k)))
                ec.add(ecoles.get(k));
        }


        return ec;
    }

    public Ecole getEcole(int idEcole)
    {
        loadEcoleFromDatabase();
        for(int n=0;n<mEcoles.size();n++)
        {
            if (mEcoles.get(n).getId()==idEcole)
                return mEcoles.get(n);
        }
        return null;
    }

    public List<Matiere> getListeMatieresEnseignant(int idEnseignant)
    {
        loadEnseignerFromDatabase();
        List<Matiere> matieres = new ArrayList<>();
        loadMatiereFromDatabase();

        for(int m=0;m<mEnseigners.size();m++)
        {
            if(mEnseigners.get(m).getIdEnseignant()==idEnseignant)
            {
                matieres.add(getMatiere(mEnseigners.get(m).getIdMatiere()));
            }

        }


        return matieres;
    }

    public Matiere getMattiere(int idMatiere)
    {
        loadMatiereFromDatabase();
        for(int a=0;a<mMatieres.size();a++)
        {
            if(mMatieres.get(a).getId()==idMatiere)
                return mMatieres.get(a);
        }
        return null;
    }

    public List<Classe> getListeClassesEnseignant(int idEnseignant)
    {
        loadEnseignerFromDatabase();
        List<Classe> les_classes = new ArrayList<>();
        //List<Integer> listeIdClasse=new ArrayList<>();
        loadClasseFromDatabase();

        for (Enseigner enseigner: mEnseigners) {
            if(enseigner.getIdEnseignant()==idEnseignant)
                les_classes.add(getClasse(enseigner.getIdEcole()));
        }
        Log.d("les classes", les_classes.toString());
        Log.d("dm","&&&&&&&&&&&&&&&&");

        //Enlever les redondances
        List<Classe> classes= new ArrayList<>();
        for(int l=0;l<les_classes.size();l++)
        {
            if(!classes.contains(les_classes.get(l)))
                classes.add(les_classes.get(l));
        }

        return classes;
    }

    public List<String> getAnneesScolaire(){
       List<Integer> yearsInt= new ArrayList<>();
       List<String> annees = new ArrayList<>();

       loadAllEtudiers();

       for(int i=0;i<mEtudiers.size();i++)
       {
           if(yearsInt.contains(mEtudiers.get(i).getAnnee())==false)
           {
               yearsInt.add(mEtudiers.get(i).getAnnee());
           }
       }
        for (Integer it: yearsInt) {
            annees.add(String.valueOf(it));

        }
       return annees;
    }


    public Information getLastInformation()
    {
        loadInformationsFromDatabase();
        return mInformation.get(mInformation.size()-1);
    }

    public List getInfosSelonIdcoles(int idEcole)
    {
        loadInformationsFromDatabase();
        List<Information> information =new ArrayList<>();
        for(int j=0;j<mInformation.size();j++)
        {
            if(mInformation.get(j).getIdEcole()==idEcole)
                information.add(mInformation.get(j));
        }
        return  information;
    }


    public int getIdListUser(String mail) {
        loadListeUsersFromDatabase();
        int id =-1;
        for(int k=0;k<mListeUsers.size();k++)
        {
            if(mListeUsers.get(k).getMail().equals(mail))
                id = mListeUsers.get(k).getId();
        }
        return id;
    }

    public void pleinListUser()
    {
        loadAllEmplois();
        loadListeUsersFromDatabase();
        loadEcoleFromDatabase();
        loadEnseignerFromDatabase();

        //id
        for(int i=0;i<mListeUsers.size();i++)
        {
            if(mListeUsers.get(i).getType().equals("Enseignant"))
            {
                for(int u=0;u<mEnseignants.size();u++)
                {
                    if(mListeUsers.get(i).getMail().equals(mEnseignants.get(u).getMail()))
                        mListeUsers.get(i).setEnseignant(mEnseignants.get(u));
                }
            }
        }



    }

    public String getCodeEcole(int id)
    {
        loadEcoleFromDatabase();
        for(int i=0;i<mEcoles.size();i++)
        {
            if(mEcoles.get(i).getId()==id)
                return mEcoles.get(i).getCode();
        }
        return "";
    }

    public List<MesEnfants> getMesEnfantsSelonId(int id) {
        loadMesEnfantsFromDatabase();
        List<MesEnfants> enfants = new ArrayList<>();
        for(int i=0;i<mMesEnfants.size();i++)
        {
            if(mMesEnfants.get(i).getIdParent()==id)
                enfants.add(mMesEnfants.get(i));
        }
        return  enfants;
    }
}