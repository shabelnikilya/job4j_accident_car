package ru.job4j.accident.store;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

public interface Store {

    Collection<Accident> findAll();

    Accident findById(int id);

    void addOrUpdate(Accident accident);

    void delete(int id);

    Collection<AccidentType> findAllTypes();

    AccidentType findTypeById(int id);
}
