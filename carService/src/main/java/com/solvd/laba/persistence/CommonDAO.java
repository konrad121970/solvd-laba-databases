package com.solvd.laba.persistence;

public interface CommonDAO<T> {

    T getById(Long id);

    void update(T object);
}
