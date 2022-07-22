package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    {
        int id1 = id.incrementAndGet();
        rules.put(id1, Rule.of(id1, "Статья. 1"));
        int id2 = id.incrementAndGet();
        rules.put(id2, Rule.of(id2, "Статья. 2"));
        int id3 = id.incrementAndGet();
        rules.put(id3, Rule.of(id3, "Статья. 3"));
    }

    public List<Rule> findAll() {
        return rules.values().stream().toList();
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }
}
