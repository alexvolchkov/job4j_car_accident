package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleJdbcTemplate repository;

    public RuleService(RuleJdbcTemplate repository) {
        this.repository = repository;
    }

    public List<Rule> findAll() {
        return repository.findAll();
    }

    public Optional<Rule> findById(int id) {
        return repository.findById(id);
    }
}