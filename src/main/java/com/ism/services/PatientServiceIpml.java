package com.ism.services;

import com.ism.entities.Patient;
import com.ism.repositories.bd.PatientRepository;
import com.ism.repositories.core.ITables;

import java.util.ArrayList;

public class PatientServiceIpml implements PatientService {
    private ITables<Patient> patientRepository;
    public PatientServiceIpml(ITables<Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public int add(Patient data) {
        return patientRepository.insert(data);
    }

    @Override
    public ArrayList<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public int update(Patient data) {
        return patientRepository.update(data);
    }

    @Override
    public Patient show(int id) {
        return patientRepository.findByID(id);
    }

    @Override
    public int remove(int id) {
        return patientRepository.delete(id);
    }

    @Override
    public int[] remove(int[] ids) {
        int[] idsNotDelete=new int[ids.length];
        int n=0;
        for (int id = 0; id < ids.length; id++) {
            if (patientRepository.delete(id)==0) {
                idsNotDelete[n++]=id;
            }
        }
        return idsNotDelete;
    }
}
