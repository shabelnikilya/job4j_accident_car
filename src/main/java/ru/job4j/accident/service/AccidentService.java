package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentHibernate;
import ru.job4j.accident.store.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.Set;

@org.springframework.stereotype.Service
public class AccidentService implements Service {
    private final AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
    this.store = store;
}

    @Override
    public void saveOrUpdateAccident(Accident accident) {
        store.addOrUpdate(accident);
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
    public void deleteAccident(int id) {
        store.delete(id);
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return store.findAllTypes();
    }

    @Override
    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }

    @Override
    public Collection<Rule> findAllRules() {
        return store.findAllRules();
    }

    @Override
    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }

    @Override
    public Set<Rule> findRulesByIds(String[] ids) {
        return store.findRulesByIds(ids);
    }
}
