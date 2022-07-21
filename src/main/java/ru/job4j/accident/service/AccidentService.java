package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate repository;

    public AccidentService(AccidentJdbcTemplate repository) {
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
