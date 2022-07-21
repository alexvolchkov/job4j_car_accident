package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new HashMap<>();
    private static int index = 0;

    {
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    public List<Rule> findAll() {
        return rules.values().stream().toList();
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }
}
