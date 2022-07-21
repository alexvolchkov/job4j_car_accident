package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentTypeService {
    private final AccidentTypeJdbcTemplate repository;

    public AccidentTypeService(AccidentTypeJdbcTemplate repository) {
        this.repository = repository;
    }

    public Optional<AccidentType> findById(int id) {
        return repository.findById(id);
    }

    public List<AccidentType> findAll() {
        return repository.findAll();
    }
}
