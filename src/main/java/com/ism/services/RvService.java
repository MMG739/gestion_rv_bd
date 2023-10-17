package com.ism.services;

import com.ism.entities.Rv;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface RvService extends IService<Rv>{
    Date dateFormat(String date) throws ParseException;
    ArrayList<Rv> todayRv();
}
