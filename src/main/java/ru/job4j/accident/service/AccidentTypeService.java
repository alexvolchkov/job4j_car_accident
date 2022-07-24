package ru.job4j.accident.service;

import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeHibernate;

import java.util.List;
import java.util.Optional;

public class AccidentTypeService {
    private final AccidentTypeHibernate repository;

    public AccidentTypeService(AccidentTypeHibernate repository) {
        this.repository = repository;
    }

    public Optional<AccidentType> findById(int id) {
        return repository.findById(id);
    }

    public List<AccidentType> findAll() {
        return repository.findAll();
    }
}
