package ru.job4j.accident.service;

import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHibernate;

import java.util.List;
import java.util.Optional;

public class RuleService {
    private final RuleHibernate repository;

    public RuleService(RuleHibernate repository) {
        this.repository = repository;
    }

    public List<Rule> findAll() {
        return repository.findAll();
    }

    public Optional<Rule> findById(int id) {
        return repository.findById(id);
    }
}
