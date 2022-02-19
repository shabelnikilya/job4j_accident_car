package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static final AtomicInteger ACCIDENTS_ID = new AtomicInteger(3);
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    private AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
        accidents.put(1, new Accident(
                1,
                "Превышение скорости",
                "Превышена скорость на 20 км/ч",
                "г. Москва, Киевское шоссе",
                types.get(1)));
        accidents.put(2, new Accident(2,
                "Пересечение сплошной",
                "Замечен камерой при пересечении",
                "г. Москва, ст.м. Баррикадная",
                types.get(2)));
        accidents.put(3, new Accident(3,
                "Не пропустил пешехода",
                "При выходе пешехода на зебру, водитель не остановился",
                "Санкт-Петербург, Лиговский проспект",
                types.get(3)));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }

    @Override
    public void addOrUpdate(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENTS_ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    @Override
    public void delete(int id) {
        accidents.remove(id);
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return types.values();
    }

    @Override
    public AccidentType findTypeById(int id) {
        return types.get(id);
    }
}
