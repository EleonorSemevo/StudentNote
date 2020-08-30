package com.memoire.studentnote.classes;

public class EnseignerT {
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
}


