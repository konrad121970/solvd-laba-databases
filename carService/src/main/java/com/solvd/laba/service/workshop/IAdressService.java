package com.solvd.laba.service.workshop;

import com.solvd.laba.domain.workshop.Adress;

import java.util.List;

public interface IAdressService {

    void createAdress(Adress adress);

    Adress getAdressById(Long id);

    List<Adress> getAllAdresses();
}