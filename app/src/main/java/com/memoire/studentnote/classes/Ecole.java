package com.memoire.studentnote.classes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Ecole implements Parcelable {
    private String id;
    private String nom;
    private String ville;
    private String quartier;
    private String type;
    private String code;

    public Ecole(String id, String nom, String ville, String quartier, String type, String code) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.quartier = quartier;
        this.type = type;
        this.code = code;
    }

    private Ecole(Parcel in) {
        id = in.readString();
        nom = in.readString();
        ville = in.readString();
        quartier = in.readString();
        type = in.readString();
        code = in.readString();
    }

    public static final Parcelable.Creator<Ecole> CREATOR = new Parcelable.Creator<Ecole>() {
        @Override
        public Ecole createFromParcel(Parcel in) {
            return new Ecole(in);
        }

        @Override
        public Ecole[] newArray(int size) {
            return new Ecole[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String getCompareKey()
    {
        return   id + "|"+ nom + "|" + ville +"|" +quartier +"|" + type +"|" +
                code ;
    }


    @Override
    public boolean equals(@Nullable Object obj)
    {
        if (this ==obj) return true;
        if(obj == null || getClass()!= obj.getClass())
            return false;
        Ecole that = (Ecole) obj;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(id);
        dest.writeString(nom);
        dest.writeString(ville);
        dest.writeString(quartier);
        dest.writeString(type);
        dest.writeString(code);
    }

}
