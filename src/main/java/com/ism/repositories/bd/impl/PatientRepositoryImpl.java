package com.ism.repositories.bd.impl;

import com.ism.entities.Patient;
import com.ism.entities.Personne;
import com.ism.repositories.bd.PatientRepository;
import com.ism.repositories.core.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientRepositoryImpl implements PatientRepository {
    //TODO: créer une enum pour les request
    private final String SQL_INSERT = "INSERT INTO `personnes` (`id`, `nomComplet`, `type`, `antecedent`) VALUES (NULL, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE `personnes` SET `nomComplet` = ? WHERE `id` = ? AND type = 'Patient";
    private final String SQL_FIND_ALL = "SELECT id,nomComplet,antecedent FROM personnes WHERE type = 'Patient'";
    private final String SQL_FIND_BY_ID = "SELECT id, nomComplet,antecedent FROM personnes WHERE type = 'Patient' AND id = ?";
    private final String SQL_DELETE = "DELETE FROM `personnes` WHERE id = ? AND type = 'Patient";

    //*injection de dependence
    private Database database;
    //*Constructeur
    public PatientRepositoryImpl(Database database) {
        this.database = database;
    }
    @Override
    public int insert(Patient data) {
        int nbrLigne = 0;
        try {
            database.openConnexion();
            database.prepareStatement(SQL_INSERT,data.getNomComplet(),data.getType(),data.getAntecedent());
            nbrLigne=database.executeUpdate();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request %s", PatientRepositoryImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public int update(Patient data) {
        return 0;
    }

    @Override
    public ArrayList<Patient> findAll() {
        ArrayList<Patient> patients=new ArrayList<>();
        try {
            database.openConnexion();
            database.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = database.executeSelect(SQL_FIND_ALL);
            while (rs.next()) {

                Patient patient=new Patient(
                        //converti le typeBD en type Java
                        rs.getInt("id"),
                        rs.getString("nomComplet"),
                        rs.getString("antecedent")
                );
                patients.add(patient);
            }
            database.closeConnexion();
            rs.close();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request %s", PatientRepositoryImpl.class);
        }
        return patients;
    }

    @Override
    public Patient findByID(int id) {
        Patient patient = null;
        try {
            database.openConnexion();
            database.prepareStatement(SQL_FIND_BY_ID, id);
            ResultSet rs = database.executeSelect(SQL_FIND_BY_ID);
            if (rs.next()) {
                patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("nomComplet"),
                        rs.getString("antecedent")
                );
            }
            database.closeConnexion();
            rs.close();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request %s", PatientRepositoryImpl.class);

            }
        if(patient!=null)
            return patient;
        else
            System.out.println("patient introuvable");
        return null;

    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int indexOf(int id) {
        return 0;
    }
}
