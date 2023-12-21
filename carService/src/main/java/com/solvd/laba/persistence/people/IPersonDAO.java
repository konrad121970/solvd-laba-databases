package com.solvd.laba.persistence.people;

import com.solvd.laba.persistence.CommonDAO;
import com.solvd.laba.domain.people.Person;

public interface IPersonDAO<T extends Person> extends CommonDAO<T> {
}
