package com.solvd.laba;

import com.solvd.laba.domain.workshop.Adress;
import com.solvd.laba.service.workshop.IAdressService;
import com.solvd.laba.service.workshop.impl.AdressServiceImpl;

public class Main {
    public static void main(String[] args) {

        IAdressService adressService = new AdressServiceImpl();

        Adress adress = new Adress();
        adress.setCity("Hajnowka");
        adress.setStreet("Main");
        adress.setBuildingNumber("123");
        adress.setPostalCode("17-200");

        adressService.createAdress(adress);

    }
}