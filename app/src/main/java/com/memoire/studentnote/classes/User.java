package com.memoire.studentnote.classes;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String telephone;
    private String type;


    public User(int id, String nom, String prenom, String mail, String mdp, String telephone, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.telephone = telephone;
        this.type = type;
    }
}
