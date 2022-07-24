package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;

public class RuleHibernate {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Rule> findAll() {
        return CommonMethods.tx(session -> session.createQuery(
                "select distinct r from Rule r"
        ).list(), sf);
    }

    public Optional<Rule> findById(int id) {
        return CommonMethods.tx(session -> Optional.ofNullable(session.get(Rule.class, id)), sf);
    }
}
