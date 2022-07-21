package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private static int index = 0;

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
}
