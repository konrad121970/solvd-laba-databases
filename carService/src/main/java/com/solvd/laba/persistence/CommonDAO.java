package com.solvd.laba.persistence;

import java.util.List;

public interface CommonDAO<T> {

    T getById(Long id);

    List<T> getAll();
}
