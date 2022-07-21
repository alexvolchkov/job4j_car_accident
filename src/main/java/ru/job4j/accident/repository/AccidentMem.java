package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, Accident.of(1, "Accident 1", "Text accident 1", "Address accident 1"));
        accidents.put(2, Accident.of(2, "Accident 2", "Text accident 2", "Address accident 2"));
        accidents.put(3, Accident.of(3, "Accident 3", "Text accident 3", "Address accident 3"));
        accidents.put(4, Accident.of(4, "Accident 4", "Text accident 4", "Address accident 4"));
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }
}
