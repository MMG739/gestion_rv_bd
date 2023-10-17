package com.ism.entities;

import java.util.Date;

public class Rv extends AbstractEntity{
    private Date date;
    private Medecin medecin;
    private Patient patient;
    private static int count ;

    public Rv() {
        super(count++);
    }

    public Rv(Date date, Medecin medecin, Patient patient) {
        super(count++);
        this.date = date;
        this.medecin = medecin;
        this.patient = patient;
    }

    public Rv(int id, Date date, Medecin medecin, Patient patient) {
        super(id);
        this.date = date;
        this.medecin = medecin;
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Personne findPersonne(int id){
        Personne personne = null;
        if(medecin.getId() == id)
            personne = medecin;
        else if(patient.getId() == id)
            personne = patient;
        else
            System.out.println("Personne non trouvée");
        return personne;
    }

    @Override
    public String toString() {
        return "Rv{" +
                "date=" + date +
                ", medecin=" + medecin +
                ", patient=" + patient +
                "} " + super.toString();
    }
}
