package com.solvd.laba.persistence;

import java.util.List;

public interface CommonDAO<T> {
    void create(T object);

    T getById(Long id);

    List<T> getAll();
}
