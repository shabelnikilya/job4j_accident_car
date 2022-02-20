package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentRepository;
import ru.job4j.accident.store.AccidentTypeRepository;
import ru.job4j.accident.store.RuleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Service
public class AccidentRepositoryService implements Service {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository typeRepository;
    private final RuleRepository ruleRepository;

    public AccidentRepositoryService(AccidentRepository accidentRepository,
                                     AccidentTypeRepository typeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.typeRepository = typeRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public void saveOrUpdateAccident(Accident accident) {
        accidentRepository.save(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return (Collection) accidentRepository.findAll();
    }

    @Override
    public Accident findById(int id) {
        return accidentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAccident(int id) {
        accidentRepository.deleteById(id);
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return (Collection) typeRepository.findAll();
    }

    @Override
    public AccidentType findTypeById(int id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Rule> findAllRules() {
        return (Collection) ruleRepository.findAll();
    }

    @Override
    public Rule findRuleById(int id) {
        return ruleRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Rule> findRulesByIds(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String id : ids) {
            result.add((findRuleById(Integer.parseInt(id))));
        }
        return result;
    }
}
