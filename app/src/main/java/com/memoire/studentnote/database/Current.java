package com.memoire.studentnote.database;

import com.memoire.studentnote.classes.MesEnfants;

public class Current {
    private Current(){}
    public static int currentIdEcole;
    public static int currentIdClasse;

    /////////
    //Pour emplois du temps
    public static MesEnfants carteEmplois;

    public Current getInstance()
    {
        return new Current();
    }
}
