package com.memoire.studentnote.classes;

public class ListeUser {

    int id;
    private String uid;
    private String nom;
    private String prenom;
    private String mail;
    private String type;

    //


    public com.memoire.studentnote.classes.Enseignant getEnseignant() {
        return Enseignant;
    }

    public void setEnseignant(com.memoire.studentnote.classes.Enseignant enseignant) {
        Enseignant = enseignant;
    }

    private Enseignant Enseignant;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ListeUser(int id, String uid, String nom, String prenom, String mail, String type) {
        this.id = id;
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


