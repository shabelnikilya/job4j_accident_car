package ru.job4j.accident.store;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Set;

public interface Store {

    Collection<Accident> findAll();

    Accident findById(int id);

    void addOrUpdate(Accident accident);

    void delete(int id);

    Collection<AccidentType> findAllTypes();

    AccidentType findTypeById(int id);

    Collection<Rule> findAllRules();

    Rule findRuleById(int id);

    Set<Rule> findRulesByIds(String[] id);

}
