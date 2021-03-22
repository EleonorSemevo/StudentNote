package com.memoire.studentnote.classes;

import android.os.Parcel;

public class Emplois implements android.os.Parcelable {
    public Emplois(int id, int idEcole, int idClasse,
                   int idMatiere, String jour,
                   int heure_debut, int heure_fin,
                   int anneScolaire, String nomMatiere,
                   int idEnseignant, String nomEnseignant,
                   String nomEcole, String nomClasse) {
        this.id = id;
        this.idEcole = idEcole;
        this.idClasse = idClasse;
        this.idMatiere = idMatiere;
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.anneScolaire = anneScolaire;
        this.nomMatiere = nomMatiere;
        this.idEnseignant = idEnseignant;
        this.nomEnseignant = nomEnseignant;
        this.nomEcole = nomEcole;
        this.nomClasse = nomClasse;
    }

    protected Emplois(Parcel in) {
        id = in.readInt();
        idEcole = in.readInt();
        idClasse = in.readInt();
        idMatiere = in.readInt();
        jour = in.readString();
        heure_debut = in.readInt();
        heure_fin = in.readInt();
        anneScolaire = in.readInt();
        nomMatiere = in.readString();
        idEnseignant = in.readInt();
        nomEnseignant = in.readString();
        nomEcole = in.readString();
        nomClasse = in.readString();
    }

    public static final Creator<Emplois> CREATOR = new Creator<Emplois>() {
        @Override
        public Emplois createFromParcel(Parcel in) {
            return new Emplois(in);
        }

        @Override
        public Emplois[] newArray(int size) {
            return new Emplois[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int idEcole;
    private int idClasse;
    private int idMatiere;
    private String jour;
    private int heure_debut;
    private int heure_fin;
    private int anneScolaire;

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    private String nomMatiere;



    private int idEnseignant;
    private String nomEnseignant;
    private String nomEcole;
    private String nomClasse;

    public Emplois(int id, int idEcole, int idClasse,  int idMatiere, String jour, int heure_debut, int heure_fin, int annee_scolaire) {
        this.idEcole = idEcole;
        this.idClasse = idClasse;
        this.idMatiere = idMatiere;
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.anneScolaire = annee_scolaire;
        this.id = id;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public int getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(int heure_debut) {
        this.heure_debut = heure_debut;
    }

    public int getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(int heure_fin) {
        this.heure_fin = heure_fin;
    }

    public int getAnneScolaire() {
        return anneScolaire;
    }

    public void setAnneScolaire(int anneScolaire) {
        this.anneScolaire = anneScolaire;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idEcole);
        dest.writeInt(idClasse);
        dest.writeInt(idMatiere);
        dest.writeString(jour);
        dest.writeInt(heure_debut);
        dest.writeInt(heure_fin);
        dest.writeInt(anneScolaire);
        dest.writeString(nomMatiere);
        dest.writeInt(idEnseignant);
        dest.writeString(nomEnseignant);
        dest.writeString(nomEcole);
        dest.writeString(nomClasse);
    }

    @Override
    public String toString() {
        return "Emplois{" +
                "id=" + id +
                ", idEcole=" + idEcole +
                ", idClasse=" + idClasse +
                ", idMatiere=" + idMatiere +
                ", jour='" + jour + '\'' +
                ", heure_debut='" + heure_debut + '\'' +
                ", heure_fin='" + heure_fin + '\'' +
                ", anneScolaire=" + anneScolaire +
                ", nomMatiere='" + nomMatiere + '\'' +
                ", idEnseignant=" + idEnseignant +
                ", nomEnseignant='" + nomEnseignant + '\'' +
                ", nomEcole='" + nomEcole + '\'' +
                ", nomClasse='" + nomClasse + '\'' +
                '}';
    }


}
