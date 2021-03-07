package com.memoire.studentnote.classes;

public class ListeUser {
    private String uid;
    private String nom;
    private String prenom;
    private String mail;


    public ListeUser(String uid, String nom, String prenom, String mail) {
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
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
}
