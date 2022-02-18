package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface Service {

    void saveAccident(Accident accident);

    Collection<Accident> findAll();

    Accident findById(int id);

    void updateAccident(Accident accident);

    void deleteAccident(int id);

}
