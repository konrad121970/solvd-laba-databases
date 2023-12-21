package com.solvd.laba.dao;

import java.util.List;

public interface CommonDAO<T>{
    void create(T object);
    T getById(Long id);
    List<T> getAll();
}
