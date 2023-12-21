package com.solvd.laba.dao;

import java.util.List;

public interface GenericDAO <T>{
    void create(T object);
    T getById(Long id);
    List<T> getAll();
}
