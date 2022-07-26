package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;
import java.util.Optional;

public class AccidentService {
    private final AccidentHibernate repository;

    public AccidentService(AccidentHibernate repository) {
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

}
