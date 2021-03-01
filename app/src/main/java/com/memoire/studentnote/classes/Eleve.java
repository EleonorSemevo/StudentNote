package com.memoire.studentnote.classes;

import android.os.Parcel;

import androidx.annotation.NonNull;

public class Eleve implements android.os.Parcelable{
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String dateNaissance;
    private String matricule;

    public static final Creator<Eleve> CREATOR = new Creator<Eleve>() {
        @Override
        public Eleve createFromParcel(Parcel in) {
            return new Eleve(in);
        }

        @Override
        public Eleve[] newArray(int size) {
            return new Eleve[size];
        }
    };

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Eleve(int id, String nom, String prenom, String sexe, String dateNaissance, String matricule) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
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

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(sexe);
        dest.writeString(dateNaissance);
        dest.writeString(matricule);
    }

    private Eleve(android.os.Parcel in) {
        id = in.readInt();
        nom = in.readString();
        prenom = in.readString();
        dateNaissance = in.readString();
        sexe = in.readString();
        matricule = in.readString();
    }

    private String getCompareKey()
    {
        return   id + "|"+ nom + "|" + prenom +"|" +sexe +"|" + dateNaissance +"|" +
                matricule ;
    }

    @NonNull
    @Override
    public String toString() {
        return getCompareKey();
    }
}
