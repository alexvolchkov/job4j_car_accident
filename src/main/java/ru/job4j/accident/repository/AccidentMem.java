package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private static int index = 0;

    {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public void create(Accident accident) {
        int id = ++index;
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
