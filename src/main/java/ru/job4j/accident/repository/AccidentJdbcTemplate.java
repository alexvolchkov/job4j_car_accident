package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final AccidentTypeJdbcTemplate typeRepository;
    private final RuleJdbcTemplate ruleRepository;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, AccidentTypeJdbcTemplate typeRepository, RuleJdbcTemplate ruleRepository) {
        this.jdbc = jdbc;
        this.typeRepository = typeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Accident create(Accident accident) {
        jdbc.update("insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
        for (Rule rule : accident.getRules()) {
            addRule(accident, rule);
        }
        return accident;
    }

    public List<Accident> findAll() {
        return jdbc.query("select * from accident",
                (rs, row) -> createAccidentFromResultSet(rs));
    }

    public Optional<Accident> findById(int id) {
        return jdbc.query("select * from accident where id = ?",
                        new Object[] {id},
                        (rs, row) -> createAccidentFromResultSet(rs))
                .stream().findAny();
    }

    public void update(Accident accident) {
        jdbc.update("update accident set name = ?, "
                        + "text = ?,"
                        + "address = ?,"
                        + "type_id = ? "
                        + "where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId());
        for (Rule rule : accident.getRules()) {
            addRule(accident, rule);
        }
    }

    private Accident createAccidentFromResultSet(ResultSet rs) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setType(typeRepository.findById(rs.getInt("type_id")).get());
        accident.setRules(new HashSet<>(ruleRepository.findRuleByAccidentId(accident.getId())));
        return accident;
    }

    private void addRule(Accident accident, Rule rule) {
        jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)",
                accident.getId(),
                rule.getId());
    }
}
