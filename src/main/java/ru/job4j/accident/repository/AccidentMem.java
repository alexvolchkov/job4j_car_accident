package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    {
        int id1 = id.incrementAndGet();
        types.put(id1, AccidentType.of(id1, "Две машины"));
        int id2 = id.incrementAndGet();
        types.put(id2, AccidentType.of(id2, "Машина и человек"));
        int id3 = id.incrementAndGet();
        types.put(id3, AccidentType.of(id3, "Машина и велосипед"));
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public void create(Accident accident) {
        int id = this.id.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public List<AccidentType> findAllTypes() {
        return types.values().stream().toList();
    }

    public Optional<AccidentType> findTypeById(int id) {
        return Optional.ofNullable(types.get(id));
    }
}
