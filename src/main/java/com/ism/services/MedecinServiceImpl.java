package com.ism.services;

import com.ism.entities.Medecin;
import com.ism.repositories.core.ITables;

import java.util.ArrayList;

public class MedecinServiceImpl implements MedecinService  {

    //Couplage faible
    private ITables<Medecin> medecinRepository;
    //Injection de dépendance via le constructeur
    public MedecinServiceImpl(ITables<Medecin> medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    @Override
    public int add(Medecin data) {
        return medecinRepository.insert(data);
    }

    @Override
    public ArrayList<Medecin> getAll() {
        return medecinRepository.findAll();
    }

    @Override
    public int update(Medecin data) {
        return medecinRepository.update(data);
    }

    @Override
    public Medecin show(int id) {
        return medecinRepository.findByID(id);
    }

    @Override
    public int remove(int id) {
        return medecinRepository.delete(id);
    }

    @Override
    public int[] remove(int[] ids) {
        int[] idsNotDelete=new int[ids.length];
        int n=0;
        for (int id = 0; id < ids.length; id++) {
            if (medecinRepository.delete(id)==0) {
                idsNotDelete[n++]=id;
            }
        }
        return idsNotDelete;
    }
}
