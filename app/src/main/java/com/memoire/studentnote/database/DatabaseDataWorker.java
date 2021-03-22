package com.memoire.studentnote.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.memoire.studentnote.classes.Information;

import static com.memoire.studentnote.database.DatabaseUtil.mdb;


public class DatabaseDataWorker {
//    private SQLiteDatabase mdb;
    private final DataManager dm;

    public DatabaseDataWorker(SQLiteDatabase mdb) {
        //DatabaseUtil.mdb = mdb;
        dm = DataManager.getInstance();

    }

    public void insertParent(String nom, String prenom, String mail, String telephone, String password)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.ParentEntry._ID,id);
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
        //values.put(DatabaseContract.EcoleEntry.COLUMN_ID, dm.sizeEcole()+"Ecole");
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
        //values.put(DatabaseContract.EnseignantEntry.COLUMN_ID,dm.sizeEnseignant()+"Enseignant");
        values.put(DatabaseContract.EnseignantEntry.COLUMN_NOM,nom);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_PRENOM, prenom);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_MAIL, mail);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_TELEPHONE,telephone);
        values.put(DatabaseContract.EnseignantEntry.COLUMN_MDP, password);

        long newRowId = mdb.insert(DatabaseContract.EnseignantEntry.TABLE_NAME, null, values);

    }

    public void insertClasse(String nom, int idEcole)
    {
        ContentValues values = new ContentValues();
       // values.put(DatabaseContract.ClasseEntry.COLUMN_ID, dm.sizeClasse()+"Classe");
        values.put(DatabaseContract.ClasseEntry.COLUMN_NOM, nom);
        values.put(DatabaseContract.ClasseEntry.COLUMN_ID_ECOLE, idEcole);
        long newRowId = mdb.insert(DatabaseContract.ClasseEntry.TABLE_NAME,null, values);
    }

    public void insertMatiere(String nom)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.MatiereEntry.COLUMN_ID,dm.sizeMatiere()+"Matiere");
        values.put(DatabaseContract.MatiereEntry.COLUMN_NOM,nom);
        long newRowId = mdb.insert(DatabaseContract.MatiereEntry.TABLE_NAME, null, values);

    }

    public void insertEnseigner(int idEcole, int idEnseignant, int idMatiere, int idClasse)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.EnseignerEntry.COLUMN_ID,dm.sizeEnseignant()+"Enseignant");
        values.put(DatabaseContract.EnseignerEntry.COLUMN_ID_ECOLE,idEcole);
        values.put(DatabaseContract.EnseignerEntry.COLUMN_ID_ENSEIGNANT, idEnseignant);
        values.put(DatabaseContract.EnseignerEntry.COLUMN_ID_MATIERE, idMatiere);
        values.put(DatabaseContract.EnseignerEntry.COLUMN_ID_CLASSE,idClasse);


        long newRowId = mdb.insert(DatabaseContract.EnseignerEntry.TABLE_NAME, null, values);

    }



    //DONNEES STATIC CHARGER AU PREALABLE D4ABORS
    public void insClasse()
    {
        insertClasse("6eme",0);
        insertClasse("5eme",1);
        insertClasse("4eme",0);
        insertClasse("3eme A",1);
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
        insertEnseigner(0,0,0,0);
        insertEnseigner(0,0,1,0);
        insertEnseigner(1,0,2,1);
        insertEnseigner(1,0,3,1);


    }

    public void ins()
    {
//        insPar();
//        insEc();
//
//        insEns();
//        insMatiere();
//        insClasse();
//        insEnseigner();
        preliminaire();
    }

    /////////////////////

    public void insertMesEnfants(int idParent, int idClasse, int idEleve, String nom, String prenom, int idEcole, String nomEcole, String classe)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.EnseignerEntry.COLUMN_ID,dm.sizeEnseignant()+"Enseignant");

        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_ID_ELEVE, idEleve);
        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_NOM, nom);
        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_PRENOM,prenom);
        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_ID_ECOLE,idEcole);
        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_NOM_ECOLE,nomEcole);
        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_CLASSE,classe);
        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_ID_CLASSE,idClasse);
        values.put(DatabaseContract.MesEnfantsEntry.COLUMN_ID_PARENT,idParent);





        long newRowId = mdb.insert(DatabaseContract.MesEnfantsEntry.TABLE_NAME, null, values);

    }

    public void insertUser(String nom, String prenom, String mail, String telephone, String motDePasse,String type)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.EnseignerEntry.COLUMN_ID,dm.sizeEnseignant()+"Enseignant");

       // values.put(DatabaseContract.MesEnfantsEntry.COLUMN_ID_ELEVE, idEleve);
        values.put(DatabaseContract.UserEntry.COLUMN_NOM, nom);
        values.put(DatabaseContract.UserEntry.COLUMN_PRENOM,prenom);
        values.put(DatabaseContract.UserEntry.COLUMN_MAIL,mail);
        values.put(DatabaseContract.UserEntry.COLUMN_TELEPHONE,telephone);
        values.put(DatabaseContract.UserEntry.COLUMN_MOTS_DE_PASSE,motDePasse);
        values.put(DatabaseContract.UserEntry.COLUMN_TYPE,type);




        long newRowId = mdb.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values);
        android.util.Log.d("insert user", newRowId+"");

    }

    public void insertEleve(String nom, String prenom, String dateNaissance, String sexe)
    {
        ContentValues values = new ContentValues();
;
        values.put(DatabaseContract.EleveEntry.COLUMN_NOM, nom);
        values.put(DatabaseContract.EleveEntry.COLUMN_PRENOM,prenom);
        values.put(DatabaseContract.EleveEntry.COLUMN_DATE_NAISSASSNCE,dateNaissance);
        values.put(DatabaseContract.EleveEntry.COLUMN_SEXE,sexe);

        long newRowId = mdb.insert(DatabaseContract.EleveEntry.TABLE_NAME, null, values);
        android.util.Log.d("insert eleve", newRowId+"");

    }

    public void insertEtudier(int idEleve, int idEcole, int idClasse, int annee, String matricule)
    {
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.EtudierEntry.COLUMN_ID_ELEVE, idEleve);
        values.put(DatabaseContract.EtudierEntry.COLUMN_ID_ECOLE,idEcole);
        values.put(DatabaseContract.EtudierEntry.COLUMN_ID_CLASSE,idClasse);
        values.put(DatabaseContract.EtudierEntry.COLUMN_ANNEE,annee);
        values.put(DatabaseContract.EtudierEntry.COLUMN_MATRICULE, matricule);

        long newRowId = mdb.insert(DatabaseContract.EtudierEntry.TABLE_NAME, null, values);
        android.util.Log.d("Etudier enrégistrer", newRowId+"");

    }

    public void insertNote(int idMatiere, int idEleve, int idClasse, int idEcole, String type, String dateComposition, String description, int anneeScolaire, double note)
    {
        ContentValues values = new ContentValues();
        ;
        values.put(DatabaseContract.NoteEntry.COLUMN_ID_ELEVE, idEleve);
        values.put(DatabaseContract.NoteEntry.COLUMN_ID_CLASSE, idClasse);
        values.put(DatabaseContract.NoteEntry.COLUMN_ID_MATIERE, idMatiere);
        values.put(DatabaseContract.NoteEntry.COLUMN_ID_ECOLE, idEcole);

        values.put(DatabaseContract.NoteEntry.COLUMN_TYPE, type);
        values.put(DatabaseContract.NoteEntry.COLUMN_DATE_COMPOSITION, dateComposition);
        values.put(DatabaseContract.NoteEntry.COLUMN_ANNEE_SCOLAIRE, anneeScolaire);
        values.put(DatabaseContract.NoteEntry.COLUMN_DESCRIPTION, description);
        values.put(DatabaseContract.NoteEntry.COLUMN_NOTE, note);


        long newRowId = mdb.insert(DatabaseContract.NoteEntry.TABLE_NAME, null, values);
        android.util.Log.d("insert note", newRowId+"");

    }

    public void insertInformations(int idAuteur, int idEcole, String description, String datePublication)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.EnseignerEntry.COLUMN_ID,dm.sizeEnseignant()+"Enseignant");

        values.put(DatabaseContract.InformationEntry.COLUMN_ID_AUTEUR, idAuteur);
        values.put(DatabaseContract.InformationEntry.COLUMN_ID_ECOLE, idEcole);
        values.put(DatabaseContract.InformationEntry.COLUMN_DESCRIPTION, description);
        values.put(DatabaseContract.InformationEntry.COLUMN_DATE_PUBLICATION, datePublication);
        //values.put(DatabaseContract.InformationEntry.COLUMN_CHEMIN, chemin);


        long newRowId = mdb.insert(DatabaseContract.InformationEntry.TABLE_NAME, null, values);

    }

    public void insertListeUser(String uid,String nom, String prenom, String mail)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.ParentEntry._ID,id);
        values.put(DatabaseContract.ListUserEntry.COLUMN_NOM,nom);
        values.put(DatabaseContract.ListUserEntry.COLUMN_PRENOM, prenom);
        values.put(DatabaseContract.ListUserEntry.COLUMN_MAIL, mail);
        values.put(DatabaseContract.ListUserEntry.COLUMN_UID,uid);


        long newRowId = mdb.insert(DatabaseContract.ListUserEntry.TABLE_NAME, null, values);
    }

    public void insertEmplois(int idEcole, int idClasse, int idMatiere, String jour, int heur_debut, int heure_fin, int anneeScolaire)
    {
        ContentValues values = new ContentValues();
        //values.put(DatabaseContract.ParentEntry._ID,id);
        values.put(DatabaseContract.EmploisEntry.COLUMN_ID_ECOLE, idEcole);
        values.put(DatabaseContract.EmploisEntry.COLUMN_ID_CLASSE, idClasse);
        values.put(DatabaseContract.EmploisEntry.COLUMN_ID_MATIERE, idMatiere);
        values.put(DatabaseContract.EmploisEntry.COLUMN_JOUR,jour);
        values.put(DatabaseContract.EmploisEntry.COLUMN_HEURE_DEBUT, heur_debut);
        values.put(DatabaseContract.EmploisEntry.COLUMN_HEURE_FIN, heure_fin);
        values.put(DatabaseContract.EmploisEntry.COLUMN_ANNEE_SCOLAIRE, anneeScolaire);


        long newRowId = mdb.insert(DatabaseContract.EmploisEntry.TABLE_NAME, null, values);
    }

    ////////////////////////INSERTION PRELIMINAIRE

    public void preliminaire()
    {
        insertUser("SEMEVO","Loren","eleonorsemevo@gmail.com","69874512","azerty","Enseignant");
        insertUser("TAMOU","Tarille","tarille@gmail.com","65879562","tatata","Parent");
        insertUser("SOLA","Fleur","fleursemevo@gmail.com","65879562","aaaaaaa","Enseignant");

        insertUser("SEDA","Ramine","lor@gmail.com","89563214","aaaaaa","Enseignant");
        insertUser("SOSSA","Marine","marine@gmail.com","77896521","aaaaaa","Enseignant");



        insertEleve("SOTA","Tarisse","08:04:06","F");
        insertEleve("FASSA","Saminta","08:04:06","M");
        insertEleve("LOLA","Tarse","08:03:06","F");
        insertEleve("MAMA","Mariline","09:04:06","M");

        insertEcole("CEG 1 CALAVI","CALAVI","Cavi","CEG","000");
        insertEcole("CEG 2 CALAVI","CALAVI","Coda","CEG","111");
        insertEcole("LES RACINESI","COTONOU","TOKPA","PRIMAIRE","065");
        insertEcole("LES RAMES","COTONOU","VOSSA","PRIMAIRE","067");

        insertMatiere("Mathématiques");
        insertMatiere("SPCT");
        insertMatiere("Français");
        insertMatiere("EPS");
        insertMatiere("Histoire-Géoghraphie");
        insertMatiere("Anglais");
        insertMatiere("SVT");
        insertMatiere("Dessin");
        insertMatiere("EST");
        insertMatiere("ES");
        insertMatiere("Chant Poesie Conte");
        insertMatiere("Ecriture");


        insertClasse("6eme",1);
        insertClasse("5eme",1);
        insertClasse("3eme",1);
        insertClasse("CI",2);
        insertClasse("CP",2);
        insertClasse("CE1",2);

        insertEtudier(1,1,1,2017,"1147" );
        insertEtudier(2,1,1,2017,"1478");
        insertEtudier(3,1,1,2017,"1496");
        insertEtudier(4,1,1,2017,"2018");

        insertMesEnfants(1,1,1,"SATI","Ramida",1,"CEG 1 CALAVI","5eme");
        insertMesEnfants(1,1,2,"SOSSA","Romuald",1,"CEG 2 CALAVI","4eme");
        insertMesEnfants(1,1,3,"GAMOUDOU","Thomas",1,"LES RAMES","5eme");
        insertMesEnfants(1,1,4,"FATOU","Doriane",1,"LES RAMES","4eme");

        insertInformations(1,1,"Loren ipsum Loren ipsum Loren ipsum Loren ipsum Loren ipsum","08/04/1999");


      //  insertListeUser("00","Semevo","Loren","lorensemevo@gmail.com");
        insertListeUser("010","Savi","Nel","eleonorsemevo@gmail.com");
        insertListeUser("020","Somakou","Dora","fleursemevo@gmail.com");
        insertListeUser("030","Sossa","Sorelle","sorellesemevo@gmail.com");
        insertListeUser("040","Gatibou","Garelle","garellesemevo@gmail.com");

    //    insertEnseigner(1,0,1,1);
        insertEnseigner(1,2,2,2);
        insertEnseigner(1,3,3,1);
        insertEnseigner(1,3,1,1);
        insertEnseigner(1,1,2,1);
        insertEnseigner(2,1,2,3);

      //
        insertEnseignant("Larou","Sami","fleursemevo@gmail.com","azerty","98745621");
        insertEnseignant("Lora","Solam","dora@gmail.com","azerty","66758954");
        insertEnseignant("Sem","Odette","odettesemevo@gmail.com","azerty","65707651");

        insertListeUser("10000","Sem","Lor","eleonorsemevo@gmail.com");
        insertListeUser("2000","Larou","Sami","fleursemevo@gmail.com");
        insertListeUser("0125","Lora","Solam","dora@gmail.com");
        insertListeUser("fleur","Larou","Sami","fleursemevo@gmail.com");


        insertEmplois(1,1,1,"Lundi",10,12,2021);
        insertEmplois(1,1,2,"Lundi",8,10,2021);
        insertEmplois(1,1,3,"Mardi",8,10,2021);
        insertEmplois(1,1,1,"Mercredi",11,13,2021);
        insertEmplois(1,1,2,"Jeudi",10,12,2021);
        insertEmplois(1,1,3,"Vendredi",15,17,2021);







    }
    //UPDATE

    public int updateInformation(int id, String message)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.InformationEntry.COLUMN_DESCRIPTION, message);


        String selection = DatabaseContract.InformationEntry._ID + "= ?";
        String[] selectionArgs = {id+""};

        int count =mdb.update(DatabaseContract.InformationEntry.TABLE_NAME, values, selection, selectionArgs);
        return  count;
    }

    //DELETE

    public int deleteInformation(int id)
    {
        String selection = DatabaseContract.InformationEntry._ID + " = ?";
        String[] selectionArgs = {id +""};

        int deleteRows = mdb.delete(DatabaseContract.InformationEntry.TABLE_NAME,selection,selectionArgs);
        return deleteRows;
    }

    //SELECT LAST ROW

//    public Information getLastInformation()
//    {
//        ContentValues values = new ContentValues();
//        values.put(DatabaseContract.InformationEntry.COLUMN_DESCRIPTION, message);
//
//
//        String selection = DatabaseContract.InformationEntry._ID + "= ?";
//        String[] selectionArgs = {id+""};
//
//        int count =mdb.update(DatabaseContract.InformationEntry.TABLE_NAME, values, selection, selectionArgs);
//        return  count;
//    }


}

