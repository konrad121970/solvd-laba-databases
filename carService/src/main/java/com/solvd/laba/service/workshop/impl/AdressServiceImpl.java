package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.dao.workshop.IAdressDAO;
import com.solvd.laba.dao.workshop.impl.AdressDAO;
import com.solvd.laba.domain.workshop.Adress;
import com.solvd.laba.service.workshop.IAdressService;

import java.util.List;

public class AdressServiceImpl implements IAdressService {

    private final IAdressDAO adressDAO;

    public AdressServiceImpl() {
        this.adressDAO = new AdressDAO();
    }

    @Override
    public void createAdress(Adress adress) {
        adressDAO.create(adress);
    }

    @Override
    public Adress getAdressById(Long id) {
        return null;
    }

    @Override
    public List<Adress> getAllAdresses() {
        return null;
    }
}
