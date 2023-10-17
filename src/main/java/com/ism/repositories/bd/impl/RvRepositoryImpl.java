package com.ism.repositories.bd.impl;

import com.ism.entities.Medecin;
import com.ism.entities.Patient;
import com.ism.entities.Rv;
import com.ism.repositories.bd.MedecinRepository;
import com.ism.repositories.bd.PatientRepository;
import com.ism.repositories.bd.RvRepository;
import com.ism.repositories.core.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RvRepositoryImpl implements RvRepository {
    //TODO: créer une enum pour les request
    private final String SQL_INSERT = "INSERT INTO `rvs` (`id`, `date`, `medecin_id`, `patient_id`) VALUES (NULL, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE `rvs` SET `date` = ? WHERE `id` = ?";
    private final String SQL_FIND_ALL = "SELECT id,date,medecin_id,patient_id FROM rvs";
    private final String SQL_FIND_BY_ID = "SELECT id, date, medecin_id, patient_id FROM rvs WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM `rvs` WHERE id = ?";

    //*injection de dependence
    private Database database;
    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;
    //*Constructeur
    public RvRepositoryImpl(Database database ,MedecinRepository medecinRepository,PatientRepository patientRepository) {
        this.database = database;
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
    }
    @Override
    public int insert(Rv data) {
        int nbrLigne = 0;
        try {
            database.openConnexion();
            database.prepareStatement(SQL_INSERT,data.getDate(),data.getMedecin().getId(),data.getPatient().getId());
            nbrLigne=database.executeUpdate();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request ici dans le insert de rv %s", PatientRepositoryImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public int update(Rv data) {
        return 0;
    }

    @Override
    public ArrayList<Rv> findAll() {
        ArrayList<Rv> rvs = new ArrayList<>();
        try {
            database.openConnexion();
            database.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = database.executeSelect(SQL_FIND_ALL);

            while (rs.next()) {
                int medecinId = rs.getInt("medecin_id");
                int patientId = rs.getInt("patient_id");

                // Vous devrez implémenter la logique pour rechercher le médecin et le patient par ID
                // à partir de votre source de données, par exemple, en utilisant un repository approprié.
                Medecin medecin = medecinRepository.findByID(medecinId);
                Patient patient = patientRepository.findByID(patientId);

                Rv rv = new Rv(
                        rs.getInt("id"),
                        rs.getDate("date"),
                        medecin,  // Utilisation de l'objet médecin trouvé
                        patient    // Utilisation de l'objet patient trouvé
                );
                rvs.add(rv);
            }

            database.closeConnexion();
            rs.close();
        } catch (SQLException e) {
            System.out.printf("Erreur execution de request %s", PatientRepositoryImpl.class);
        }
        return rvs;    }

    @Override
    public Rv findByID(int id) {
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
