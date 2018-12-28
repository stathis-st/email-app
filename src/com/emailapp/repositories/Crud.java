package com.emailapp.repositories;

import com.emailapp.domain.Entity;

import java.util.ArrayList;

public interface Crud<T extends Entity> {

    long save(T t);

    int delete(long id);

    T getOne(long id);

    ArrayList<T> getAll();
}
