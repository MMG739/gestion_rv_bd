package com.ism.repositories.bd.impl;

import com.ism.entities.Medecin;
import com.ism.entities.Patient;
import com.ism.repositories.bd.MedecinRepository;
import com.ism.repositories.core.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedecinRepositoryImpl implements MedecinRepository {
    //TODO: créer une enum pour les request
    private final String SQL_INSERT = "INSERT INTO `personnes` (`id`, `nomComplet`, `type`, `speciality`) VALUES (NULL, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE `personnes` SET `nomComplet` = ? WHERE `id` = ?";
    private final String SQL_FIND_ALL = "SELECT id,nomComplet,speciality FROM personnes WHERE type = 'Medecin'";
    private final String SQL_FIND_BY_ID = "SELECT id, nomComplet,speciality FROM personnes WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM `personnes` WHERE id = ?";

    //*injection de dependence
    private Database database;
    //*Constructeur
    public MedecinRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public int insert(Medecin data) {
        int nbrLigne = 0;
        try {
            database.openConnexion();
            database.prepareStatement(SQL_INSERT,data.getNomComplet(),data.getType(),data.getSpeciality());
            nbrLigne=database.executeUpdate();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request %s", MedecinRepositoryImpl.class);
        }
        return nbrLigne;        }

    @Override
    public int update(Medecin data) {
        return 0;
    }

    @Override
    public ArrayList<Medecin> findAll() {
        ArrayList<Medecin> medecins=new ArrayList<>();
        try {
            database.openConnexion();
            database.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = database.executeSelect(SQL_FIND_ALL);
            while (rs.next()) {

                Medecin medecin=new Medecin(
                        //converti le typeBD en type Java
                        rs.getInt("id"),
                        rs.getString("nomComplet"),
                        rs.getString("speciality")
                );
                medecins.add(medecin);
            }
            database.closeConnexion();
            rs.close();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request %s", PatientRepositoryImpl.class);
        }
        return medecins;
    }

    @Override
    public Medecin findByID(int id) {

        Medecin medecin=null;
        try {
            database.openConnexion();
            database.prepareStatement(SQL_FIND_BY_ID, id);
            ResultSet rs = database.executeSelect(SQL_FIND_BY_ID);
            if (rs.next()) {
                medecin=new Medecin(
                        rs.getInt("id"),
                        rs.getString("nomComplet"),
                        rs.getString("speciality")
                );
            }
            database.closeConnexion();
            rs.close();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request %s", PatientRepositoryImpl.class);
        }

        if(medecin!=null)
            return medecin;
        else
            System.out.println("medecin introuvable");
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int indexOf(int id) {
        ArrayList<Medecin> meds= this.findAll();
        int pos=0;
        for (Medecin cat : meds ) {
            if(cat.getId()==id){
                return pos;
            }
            pos++;
        }
        return -1;
    }
}
