package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.Store;

import java.util.Collection;

@org.springframework.stereotype.Service
public class AccidentService implements Service {
    private final Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    @Override
    public void saveAccident(Accident accident) {
        store.add(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return store.findAll();
    }

    @Override
    public Accident findById(int id) {
        return store.findById(id);
    }

    @Override
    public void updateAccident(Accident accident) {
        store.update(accident);
    }

    @Override
    public void deleteAccident(int id) {
        store.delete(id);
    }
}
