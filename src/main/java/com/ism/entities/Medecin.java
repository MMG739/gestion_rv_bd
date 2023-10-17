package com.ism.entities;

import lombok.Getter;

@Getter
public class Medecin extends Personne {
    private String speciality;



    public Medecin(String nomComplet, String speciality) {
        super(nomComplet);
        this.speciality = speciality;
        this.type= "Medecin";
    }
    public Medecin(int id,String nomComplet, String speciality) {
        super(id,nomComplet);
        this.speciality = speciality;
        this.type= "Medecin";
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "speciality='" + speciality + '\'' +
                "} " + super.toString();
    }
}
