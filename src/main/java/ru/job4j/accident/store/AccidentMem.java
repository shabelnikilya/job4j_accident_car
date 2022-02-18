package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static final AtomicInteger ACCIDENTS_ID = new AtomicInteger(3);

    private AccidentMem() {
        accidents.put(1, new Accident(1,
                "Превышение скорости",
                "Превышена скорость на 20 км/ч",
                "г. Москва, Киевское шоссе"));
        accidents.put(2, new Accident(2,
                "Пересечение сплошной",
                "Замечен камерой при пересечении",
                "г. Москва, ст.м. Баррикадная"));
        accidents.put(3, new Accident(3,
                "Не пропустил пешехода",
                "При выходе пешехода на зебру, водитель не остановился",
                "Санкт-Петербург, Лиговский проспект"));
    }

    private static final class Instance {
        private static final Store STORE = new AccidentMem();
    }

    public static Store getInstance() {
        return Instance.STORE;
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
    public void add(Accident accident) {
        accident.setId(ACCIDENTS_ID.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    @Override
    public void delete(int id) {
        accidents.remove(id);
    }

    @Override
    public void update(Accident accident) {
        if (accident.getId() != 0) {
            accidents.put(accident.getId(), accident);
        }
    }
}
