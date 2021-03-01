package com.memoire.studentnote.database;

public class Current {
    private Current(){}
    public static int currentIdEcole;
    public static int currentIdClasse;

    public Current getInstance()
    {
        return new Current();
    }
}
