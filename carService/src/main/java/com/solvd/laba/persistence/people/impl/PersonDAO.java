package com.solvd.laba.persistence.people.impl;

import com.solvd.laba.persistence.people.IPersonDAO;
import com.solvd.laba.domain.people.Person;

public abstract class PersonDAO<T extends Person> implements IPersonDAO<T> {
}
