package com.ism.entities;

import lombok.Getter;

@Getter
public class Patient extends Personne {
    private String antecedent;

    public Patient(String nomComplet, String antecedent) {
        super(nomComplet);
        this.antecedent = antecedent;
        this.type="Patient";

    }

    public Patient(int id, String nomComplet, String antecedent) {
        super(id, nomComplet);
        this.antecedent = antecedent;
        this.type="Patient";
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "antecedent='" + antecedent + '\'' +
                "} " + super.toString();
    }
}
