package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Optional;

public class AccidentTypeHibernate {
    private final SessionFactory sf;

    public AccidentTypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<AccidentType> findAll() {
        return CommonMethods.tx(session -> session.createQuery(
                "select distinct t from AccidentType t"
        ).list(), sf);
    }

    public Optional<AccidentType> findById(int id) {
        return CommonMethods.tx(session -> Optional.ofNullable(session.get(AccidentType.class, id)), sf);
    }
}
