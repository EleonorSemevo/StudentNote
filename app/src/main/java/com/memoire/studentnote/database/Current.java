package com.memoire.studentnote.database;

public class Current {
    private Current(){}
    public static String currentIdEcole;
    public static String currentIdClasse;

    public Current getInstance()
    {
        return new Current();
    }
}
