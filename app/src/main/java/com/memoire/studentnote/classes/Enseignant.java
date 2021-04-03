package com.memoire.studentnote.classes;


import androidx.annotation.Nullable;

public class Enseignant {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String telephone;

    public Enseignant(int id)
    {
        this.id= id;
    }

    public Enseignant(int id, String nom, String prenom, String mail, String mdp, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.telephone = telephone;
    }
    public Enseignant(){}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass()!=obj.getClass())
            return false;
        Enseignant that = (Enseignant) obj;
        return getCompareKey().equals(that.getCompareKey());
    }

    private String getCompareKey()
    {

        return  "id: "+ id + " | nom: "+ nom + " | prenom: " + prenom
                +" | mail: "+ mail + " mdp: " + mdp +
                " telephone : "+ telephone;
    }
}
