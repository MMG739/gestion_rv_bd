package com.ism.services;

import com.ism.entities.Patient;
import com.ism.entities.Rv;
import com.ism.repositories.core.ITables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RvServiceImpl implements RvService {
    private ITables<Rv> rvRepository;

    public RvServiceImpl(ITables<Rv> rvRepository) {
        this.rvRepository = rvRepository;
    }

    @Override
    public int add(Rv data) {
        return rvRepository.insert(data);
    }

    @Override
    public ArrayList<Rv> getAll() {
        return rvRepository.findAll();
    }

    @Override
    public int update(Rv data) {
        return rvRepository.update(data);
    }

    @Override
    public Rv show(int id) {
        return rvRepository.findByID(id);
    }

    @Override
    public int remove(int id) {
        return rvRepository.delete(id);
    }

    @Override
    public int[] remove(int[] ids) {
        int[] idsNotDelete=new int[ids.length];
        int n=0;
        for (int id = 0; id < ids.length; id++) {
            if (rvRepository.delete(id)==0) {
                idsNotDelete[n++]=id;
            }
        }
        return idsNotDelete;
    }

    @Override
    public Date dateFormat(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
    @Override
    public ArrayList<Rv> todayRv() {
        ArrayList<Rv> rvList = rvRepository.findAll();
        ArrayList<Rv> result = new ArrayList<>();
        Date currentDate = new Date();

        for (Rv rv : rvList) {
            if (isSameDate(rv.getDate(), currentDate)) {
                result.add(rv);
            }
        }

        return result;
    }

    // Utility method to check if two dates have the same day, month, and year.
    private boolean isSameDate(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate1 = dateFormat.format(date1);
        String strDate2 = dateFormat.format(date2);

        return strDate1.equals(strDate2);
    }
}


