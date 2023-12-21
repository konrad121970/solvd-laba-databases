package com.solvd.laba.dao.people;

import com.solvd.laba.dao.GenericDAO;
import com.solvd.laba.domain.people.Person;

public interface IPersonDAO<T extends Person> extends GenericDAO<T> {
}