package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentMem repository;

    public AccidentService(AccidentMem repository) {
        this.repository = repository;
    }

    public List<Accident> findAll() {
        return repository.findAll();
    }

    public void create(Accident accident) {
        repository.create(accident);
    }

    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }

    public void update(Accident accident) {
        repository.update(accident);
    }

    public List<AccidentType> findAllTypes() {
        return repository.findAllTypes();
    }

    public Optional<AccidentType> findTypeById(int id) {
        return repository.findTypeById(id);
    }
}
