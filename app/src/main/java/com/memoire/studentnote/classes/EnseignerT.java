package com.memoire.studentnote.classes;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EnseignerT implements android.os.Parcelable {
    private Enseignant enseignant;
    private Matiere matiere;
    private Ecole ecole;
    private Classe classe;


    public EnseignerT(Ecole ecole,Classe classe, Matiere matiere,Enseignant enseignant) {
        this.enseignant = enseignant;
        this.matiere = matiere;
        this.ecole = ecole;
        this.classe = classe;
    }

    protected EnseignerT(Parcel in) {
        ecole = in.readParcelable(Ecole.class.getClassLoader());
        classe = in.readParcelable(Classe.class.getClassLoader());
    }

    public static final Creator<EnseignerT> CREATOR = new Creator<EnseignerT>() {
        @Override
        public EnseignerT createFromParcel(Parcel in) {
            return new EnseignerT(in);
        }

        @Override
        public EnseignerT[] newArray(int size) {
            return new EnseignerT[size];
        }
    };

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(ecole, flags);
        dest.writeParcelable(classe, flags);
    }

    @NonNull
    @Override
    public String toString() {
        return getString();
    }

    private String getString() {
        return "Enseignant: " +enseignant.toString()+" Ecole: "+
                ecole.toString()+" Matiere: "+
                matiere.toString()+ " Classe: "+ classe.toString();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass()!=obj.getClass())
            return false;
        EnseignerT that = (EnseignerT) obj;

        return getCompareKey(that);
    }

    private boolean getCompareKey(EnseignerT that)
    {
        return  (that.classe.equals(this.classe) &&
                that.matiere.equals(this.matiere) &&
                that.ecole.equals(this.ecole) &&
                that.enseignant.equals(this.enseignant));


    }
}


