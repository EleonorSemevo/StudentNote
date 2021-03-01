package com.memoire.studentnote.database;


//import com.google.firebase.auth.FirebaseAuth;


import com.memoire.studentnote.classes.Classe;
import com.memoire.studentnote.classes.ClasseT;
import com.memoire.studentnote.classes.Ecole;
import com.memoire.studentnote.classes.Eleve;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.classes.EnseignerT;
import com.memoire.studentnote.classes.Etudier;
import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.classes.MesEnfants;
import com.memoire.studentnote.classes.Note;
import com.memoire.studentnote.classes.Parent;
import com.memoire.studentnote.classes.User;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance=null;
    private final java.util.List<Parent> mParents = new ArrayList<>();
    private final java.util.List<Note> mNotes = new ArrayList<>();
    private final java.util.List<Enseignant> mEnseignants = new ArrayList<>();
    private final java.util.List<Ecole> mEcoles = new ArrayList<>();
    private final java.util.List<Classe> mClasses = new ArrayList<>();
    private final java.util.List<Matiere> mMatieres = new ArrayList<>();
    private final java.util.List<Enseigner> mEnseigners = new ArrayList<>();
    private final java.util.List<ClasseT> mClasseTs = new ArrayList<>();
    private final java.util.List<EnseignerT> mEnseignerTs = new ArrayList<>();



    ////
    private final java.util.List<MesEnfants> mMesEnfants = new ArrayList<>();
    private final java.util.List<User> mUsers = new ArrayList<>();

    public java.util.List<Eleve> getEleves() {
        loadEleveFromDatabase();
        return mEleves;
    }

    private final java.util.List<Eleve> mEleves = new ArrayList<>();
    private final java.util.List<Etudier> mEtudiers = new ArrayList<>();


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

        final android.database.Cursor enseignantCursor = DatabaseUtil.mdb.query(DatabaseContract.ParentEntry.TABLE_NAME,
                enseignantColumns,null,null,null,null,null,null);
        //loadFromDatabase((DatabaseOpenHelper) enseignantCursor);

        int enseignantNomPos = enseignantCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_NOM);
        int enseignantPrenomPos = enseignantCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_PRENOM);
        int enseignantMailPos = enseignantCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_MAIL);
        int enseignantMdpPos = enseignantCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_MDP);
        int enseignantTelephone = enseignantCursor.getColumnIndex(DatabaseContract.ParentEntry.COLUMN_TELEPHONE);
        int enseignantId = enseignantCursor.getColumnIndex(DatabaseContract.ParentEntry._ID);

        DataManager dm =getInstance();
        dm.mParents.clear();
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

    private void loadEnseignerTFromDatabase()
    {

        loadEnseignerFromDatabase();
        DataManager dm=getInstance();

        mEnseignerTs.clear();
        for (int i=0;i<mEnseigners.size();i++)
        {
            //List<Enseigner> m =mEnseigners;

            Enseigner enseigner= mEnseigners.get(i);
            mEnseignerTs.add(new EnseignerT(dm.getEcoleSelonId(enseigner.getIdEcole()),
                    dm.getClasseSelonId(enseigner.getIdClasse()),
                    dm.getMatiereSelonId(enseigner.getIdMatiere()),
                    dm.getEnseignantSelonId(enseigner.getIdEnseignant()
                    )));
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
                DatabaseContract.EleveEntry.COLUMN_MATRICULE,
                DatabaseContract.EleveEntry.COLUMN_SEXE,
                DatabaseContract.EleveEntry.COLUMN_DATE_NAISSASSNCE

        };

        final android.database.Cursor eleveCursor = DatabaseUtil.mdb.query(DatabaseContract.EleveEntry.TABLE_NAME,
                eleveColums,null,null,null,null,null,null);

        int eleveIdPos= eleveCursor.getColumnIndex(DatabaseContract.EleveEntry._ID);
        int eleveNomPos = eleveCursor.getColumnIndex(DatabaseContract.EleveEntry.COLUMN_NOM);
        int eleveMatriculePos = eleveCursor.getColumnIndex(DatabaseContract.EleveEntry.COLUMN_MATRICULE);
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
            String matricule = eleveCursor.getString(eleveMatriculePos);
            String dateNaissaance = eleveCursor.getString(eleveDateNaissancePos);

            Eleve eleve = new Eleve(id,nom,prenom,sexe,dateNaissaance,matricule);
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
        final String[] userColumns ={
                DatabaseContract.UserEntry.COLUMN_NOM,
                DatabaseContract.UserEntry.COLUMN_PRENOM,
                DatabaseContract.UserEntry.COLUMN_MAIL,
                DatabaseContract.UserEntry.COLUMN_TELEPHONE,
                DatabaseContract.UserEntry.COLUMN_MOTS_DE_PASSE,
                DatabaseContract.UserEntry.COLUMN_TYPE,
                DatabaseContract.UserEntry._ID
        };

        final android.database.Cursor userCursor = DatabaseUtil.mdb.query(DatabaseContract.UserEntry.TABLE_NAME,
                userColumns,null,null,null,null,null,null);

        int userNomPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_NOM);
        int userPrenomPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_PRENOM);
        int userMailPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_MAIL);
        int userMdpPos = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_MOTS_DE_PASSE);
        int userTelephone = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_TELEPHONE);
        int userType = userCursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_TYPE);
        int userId = userCursor.getColumnIndex(DatabaseContract.UserEntry._ID);

        DataManager dm =getInstance();
        dm.mUsers.clear();
        while (userCursor.moveToNext())
        {
            String nom = userCursor.getString(userNomPos);
            String prenom = userCursor.getString(userPrenomPos);
            String mail = userCursor.getString(userMailPos);
            String mdp = userCursor.getString(userMdpPos);
            String telephone = userCursor.getString(userTelephone);
            int id = userCursor.getInt(userId);
            String type = userCursor.getString(userType);

            User user = new User(id,nom,prenom,mail,mdp,telephone,type);
            dm.mUsers.add(user);
        }

        userCursor.close();
    }

    public void loadAllEtudiers()
    {


        final String[] etudierColumns ={
                DatabaseContract.EtudierEntry.COLUMN_ID_ELEVE,
                DatabaseContract.EtudierEntry.COLUMN_ID_ECOLE,
                DatabaseContract.EtudierEntry.COLUMN_ID_CLASSE,
                DatabaseContract.EtudierEntry.COLUMN_ANNEE,
                DatabaseContract.EtudierEntry._ID
        };

        final android.database.Cursor etudierCursor = DatabaseUtil.mdb.query(DatabaseContract.EtudierEntry.TABLE_NAME,
                etudierColumns,null,null,null,null,null,null);

        int idEcolePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ID_ECOLE);
        int idClassePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ID_CLASSE);
        int idElevePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ID_ELEVE);
        int anneePos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry.COLUMN_ANNEE);
        int idPos = etudierCursor.getColumnIndex(DatabaseContract.EtudierEntry._ID);

        DataManager dm =getInstance();
        dm.mEtudiers.clear();
        while (etudierCursor.moveToNext())
        {
            int idEcole = etudierCursor.getInt(idEcolePos);
            int idClasse = etudierCursor.getInt(idClassePos);
            int idEleve = etudierCursor.getInt(idElevePos);
            int annee  = etudierCursor.getInt(anneePos);
            int id =etudierCursor.getInt(idPos);

            Etudier etudier = new Etudier(id,idEleve,idClasse,idEcole,annee);
            dm.mEtudiers.add(etudier);

        }

        etudierCursor.close();
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

    public Eleve getEleveChoisit(String matricule)
    {
        loadEleveFromDatabase();
        for (Eleve eleve: mEleves) {
            if(eleve.getMatricule()==matricule)
                return eleve;
        }
        return null;
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



}