package com.memoire.studentnote.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public DatabaseContract() {}

    public static final class AdministrationEntry implements BaseColumns
    {
        public static final String TABLE_NAME="Administration";
        //public static final String COLUMN_ID="idAdmin";
        public static final String COLUMN_NOM="nomAdmin";
        public static final String COLUMN_PRENOM="prenomAdmin";
        public static final String COLUMN_MAIL="mailAdmin";
        public static final String COLUMN_MDP="mdpAdmin";
        public static final String COLUMN_TELEPHONE="telephoneAdmin";
        public static final String COLUMN_ROLE="roleAdmin";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ( "+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_NOM+" TEXT NOT NULL, "+
                COLUMN_PRENOM + " TEXT NOT NULL, "+
                COLUMN_MAIL + " TEXT NOT NULL, "+
                COLUMN_MDP + " TEXT NOT NULL, "+
                COLUMN_TELEPHONE+ " TEXT, "+
                COLUMN_ROLE+ " TEXT NOT NULL )";

    }

    public static final class ClasseEntry implements BaseColumns
    {
        public static final String TABLE_NAME="classe";
       // public static final String COLUMN_ID="id";
        public static final String COLUMN_NOM="nom";
        public static final String COLUMN_ID_ECOLE="idEcole";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_NOM+ " TEXT NOT NULL, "+
                COLUMN_ID_ECOLE+ " TEXT NOT NULL )";
    }
    public static final class EcoleEntry implements BaseColumns
    {
        public static final String TABLE_NAME="ecole";
        //public static final String COLUMN_ID="id";
        public static final String COLUMN_NOM="nom";
        public static final String COLUMN_VILLE="ville";
        public static final String COLUMN_QUARTIER="quartier";
        public static final String COLUMN_TYPE="type";
        public static final String COLUMN_CODE="code";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_NOM+ " TEXT NOT NULL, "+
                COLUMN_VILLE+ " TEXT, "+
                COLUMN_QUARTIER+ " TEXT, "+
                COLUMN_TYPE+ " TEXT NOT NULL, "+
                COLUMN_CODE+" TEXT NOT NULL )";
    }

    public static final class EleveEntry implements BaseColumns
    {
        public static final String TABLE_NAME="eleve";

        public static final String COLUMN_NOM="nom";
        public static final String COLUMN_PRENOM="prenom";
        public static final String COLUMN_SEXE="sexe";
        public static final String  COLUMN_DATE_NAISSASSNCE="date_naissance";
        public static final String COLUMN_MATRICULE="matricule";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_MATRICULE+ " TEXT, "+
                COLUMN_NOM+ " TEXT NOT NULL, "+
                COLUMN_PRENOM+ " TEXT NOT NULL, "+
                COLUMN_SEXE+ " TEXT, "+
                COLUMN_DATE_NAISSASSNCE+" DATE )";


    }

    public static final class EnseignantEntry implements BaseColumns
    {
        public static final String TABLE_NAME="enseignant";
        public static final String COLUMN_NOM="nom";
        public static final String COLUMN_PRENOM="prenom";
        public static final String COLUMN_MAIL="mail";
        public static final String COLUMN_MDP="mdp";
        public static final String COLUMN_TELEPHONE="telephone";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_NOM+ " TEXT NOT NULL, "+
                COLUMN_PRENOM+ " TEXT NOT NULL, "+
                COLUMN_MAIL+ " TEXT UNIQUE NOT NULL, "+
                COLUMN_MDP+ " TEXT NOT NULL, "+
                COLUMN_TELEPHONE+ " TEXT )";


    }

    public static final class EnseignerEntry implements BaseColumns
    {
        public static final String TABLE_NAME="enseigner";
        public static final String COLUMN_ID_ENSEIGNANT = "idEnseignant";
        public static final String COLUMN_ID_MATIERE="idMatiere";
        public static final String COLUMN_ID_ECOLE="idEcole";
        public static final String COLUMN_ID_CLASSE ="idClasse";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+ " ("+_ID+ " INTEGER PRIMARY KEY, "+
                COLUMN_ID_ENSEIGNANT+ " INTEGER, "+
                COLUMN_ID_MATIERE+ " INTEGER, "+
                COLUMN_ID_CLASSE + " INTEGER, "+
                COLUMN_ID_ECOLE+ " INTEGER)";



    }

    public static final class EtudierEntry implements BaseColumns
    {
        public static final String TABLE_NAME="etudier";
        public static final String COLUMN_ID_ELEVE="idEleve";
        public static final String COLUMN_ID_CLASSE="idClasse";
        public static final String COLUMN_ID_ECOLE="idEcole";
        public static final String COLUMN_ANNEE="anne";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+ " ( "+ _ID+" INTEGER PRIMARY KEY, "+
                COLUMN_ID_ELEVE+ " INTEGER NOT NULL, "+
                COLUMN_ID_CLASSE+ " INTEGER NOT NULL, "+
                COLUMN_ID_ECOLE+ " INTEGER NOT NULL, "+
                COLUMN_ANNEE+ " INTEGER NOT NULL )";
        }


    public static final class InformationEntry implements BaseColumns
    {
        public static final String TABLE_NAME="information";
        public static final String COLUMN_DESCRIPTION="description";
        public static final String COLUMN_CHEMIN="chemin";
        public static final String COLUMN_DATE_PUBLICATION="datePublication";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+ " ( "+ _ID+" INTEGER PRIMARY KEY, "+
                COLUMN_DESCRIPTION+ " TEXT, "+
                COLUMN_CHEMIN+ " TEXT, "+
                COLUMN_DATE_PUBLICATION+ " TEXT NOT NULL )";
    }

    public static final class MatiereEntry implements BaseColumns
    {
        private String id;
        private String nom;

        public static final String TABLE_NAME="matiere";
        public static final String COLUMN_NOM="nom";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+ " ( "+_ID+ " INTEGER PRIMARY KEY, "+
                COLUMN_NOM+ " TEXT )";

    }

    public static final class NoteEntry implements BaseColumns
    {
        public static final String TABLE_NAME="note";
        public static final String COLUMN_ID_MATIERE="idMatiere";
        public static final String COLUMN_ID_ELEVE="idEleve";
        public static final String COLUMN_ID_CLASSE="idClasse";
        public static final String COLUMN_ID_ECOLE="idEcole";
        public static final String COLUMN_TYPE="type";
        public static final String COLUMN_DATE_COMPOSITION="dateComposition";
        public static final String COLUMN_DESCRIPTION="description";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+ " ( "+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_ID_MATIERE+ " INTEGER NOT NULL, "+
                COLUMN_ID_ELEVE+ " INTEGER NOT NULL, "+
                COLUMN_ID_CLASSE+ " INTEGER NOT NULL, "+
                COLUMN_ID_ECOLE+ " INTEGER NOT NULL, "+
                COLUMN_TYPE+ " TEXT NOT NULL, "+
                COLUMN_DATE_COMPOSITION+ " TEXT NOT NULL, "+
                COLUMN_DESCRIPTION+ " TEXT )";
    }

    public static final class NoteDocumentEntry implements BaseColumns
    {
        public static final String TABLE_NAME="noteDocument";
        public static final String COLUMN_ID_MATIERE="idMatiere";
        public static final String COLUMN_ID_ECOLE="idEcole";
        public static final String COLUMN_ID_CLASSE="idClasse";
        public static final String COLUMN_ID_DOCUMENT="idDocument";
        public static final String COLUMN_TYPE="type";
        public static final String COLUMN_DATE_COMPOSITION="dateComposition";
        public static final String COLUMN_LOCATION="location";
        public static final String COLUMN_NUMERO_COMPOSITION="numeroComposition";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+ " ( "+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_ID_MATIERE+ " INTEGER NOT NULL, "+
                COLUMN_ID_ECOLE+ " INTEGER NOT NULL, "+
                COLUMN_ID_CLASSE+ " INTEGER NOT NULL, "+
                COLUMN_ID_DOCUMENT+ " INTEGER UNIQUE NOT NULL, "+
                COLUMN_TYPE+ " TEXT NOT NULL, "+
                COLUMN_DATE_COMPOSITION+ " TEXT NOT NULL, "+
                COLUMN_LOCATION+ " TEXT NOT NULL, "+
                COLUMN_NUMERO_COMPOSITION+ " INTEGER NOT NULL )";

    }

    public static final class ParentEntry implements BaseColumns
    {
        public static final String TABLE_NAME="parent";
        public static final String COLUMN_NOM="nom";
        public static final String COLUMN_PRENOM="prenom";
        public static final String COLUMN_MAIL="mail";
        public static final String COLUMN_MDP="mdp";
        public static final String COLUMN_TELEPHONE="telephone";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ( "+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_NOM+ " TEXT NOT NULL, "+
                COLUMN_PRENOM+ " TEXT NOT NULL, "+
                COLUMN_MAIL+ " TEXT UNIQUE NOT NULL, "+
                COLUMN_MDP+ " TEXT NOT NULL, "+
                COLUMN_TELEPHONE+ " TEXT )";
    }


    public static final class MesEnfantsEntry implements BaseColumns
    {
        public static final String TABLE_NAME="MesEnfants";
        public static final String COLUMN_ID_ELEVE = "idEleve";
        public static final String COLUMN_NOM="nom";
        public static final String COLUMN_PRENOM="prenom";
        public static final String COLUMN_ID_ECOLE ="idEcole";
        public static final String COLUMN_NOM_ECOLE ="nomEcole";
        public static final String COLUMN_CLASSE ="classe";
        public static final String COLUMN_ID_CLASSE ="idclasse";
        public static final String COLUMN_ID_PARENT ="idParent";


        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ( "+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_ID_ELEVE+ " INTEGER UNIQUE NOT NULL, "+
                COLUMN_NOM+ " TEXT NOT NULL, "+
                COLUMN_PRENOM+ " TEXT NOT NULL, "+
                COLUMN_ID_ECOLE + " INTEGER UNIQUE NOT NULL, "+
                COLUMN_NOM_ECOLE + " TEXT NOT NULL, "+
                COLUMN_ID_CLASSE + " INTEGER NOT NULL, "+
                COLUMN_ID_PARENT + " INTEGER NOT NULL, "+
                COLUMN_CLASSE + " TEXT )";
    }

    public static final class UserEntry implements BaseColumns
    {
        public static final String TABLE_NAME="User";
        public static final String COLUMN_ID="id";
        public static final String COLUMN_NOM = "nom";
        public static final String COLUMN_PRENOM ="prenom";
        public static final String COLUMN_TELEPHONE ="telephone";
        public static final String COLUMN_MAIL ="mail";
        public static final String COLUMN_MOTS_DE_PASSE ="motDePasse";
        public static final String COLUMN_TYPE ="type";

        public static final String SQL_CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ( "+_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_NOM + " TEXT UNIQUE NOT NULL, "+
                COLUMN_PRENOM + " TEXT NOT NULL, "+
                COLUMN_TELEPHONE + " TEXT NOT NULL, "+
                COLUMN_MAIL + " TEXT UNIQUE NOT NULL, "+
                COLUMN_MOTS_DE_PASSE + " TEXT NOT NULL, "+
                COLUMN_TYPE + " TEXT )";
    }
}
