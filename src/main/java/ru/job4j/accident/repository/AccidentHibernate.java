package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident create(Accident accident) {
        CommonMethods.tx(session -> session.save(accident), sf);
        return accident;
    }

    public List<Accident> findAll() {
        return CommonMethods.tx(session -> session.createQuery(
                "select distinct a from Accident a join fetch a.type t left join fetch a.rules"
        ).list(), sf);
    }

    public Optional<Accident> findById(int id) {
        return CommonMethods.tx(session -> session.createQuery(
                        "select distinct a from Accident a join fetch a.type t left join fetch a.rules where a.id =:id"
                ).setParameter("id", id)
                .uniqueResultOptional(), sf);
    }

    public void update(Accident accident) {
        CommonMethods.tx(session ->  session.merge(accident), sf);
    }
}
