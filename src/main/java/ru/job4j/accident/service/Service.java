package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

public interface Service {

    void saveOrUpdateAccident(Accident accident);

    Collection<Accident> findAll();

    Accident findById(int id);

    void deleteAccident(int id);

    Collection<AccidentType> findAllTypes();

    AccidentType findTypeById(int id);
}
