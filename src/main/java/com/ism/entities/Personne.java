package com.ism.entities;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class Personne extends AbstractEntity {
    protected String nomComplet;
    protected String type;
    protected static int nbrP;

    public Personne() {
        super(nbrP++);
    }
    public Personne(int id, String nomComplet) {
        super(id);
        this.nomComplet = nomComplet;
    }

    public Personne(String nomComplet) {
        super(nbrP++);
        this.nomComplet = nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nomComplet='" + nomComplet + '\'' +
                ", type='" + type + '\'' +
                "} " + super.toString();
    }

}
