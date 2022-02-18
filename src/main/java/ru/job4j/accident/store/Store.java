package ru.job4j.accident.store;

import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface Store {

    Collection<Accident> findAll();

    Accident findById(int id);

    void add(Accident accident);

    void delete(int id);

    void update(Accident accident);

}