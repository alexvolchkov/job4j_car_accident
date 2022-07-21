package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc, AccidentTypeJdbcTemplate typeRepository) {
        this.jdbc = jdbc;
    }

    public List<Rule> findAll() {
        return jdbc.query("select * from rule",
                (rs, row) -> createRuleFromResultSet(rs));
    }

    public Optional<Rule> findById(int id) {
        return jdbc.query("select * from rule where id = ?",
                        new Object[] {id},
                        (rs, row) -> createRuleFromResultSet(rs))
                .stream().findAny();
    }

    public List<Rule> findRuleByAccidentId(int id) {
        return jdbc.query("select * from accident_rule a join rule r on a.rule_id = r.id where a.id = ?",
                new Object[]{id},
                (rs, row) -> createRuleFromResultSet(rs));
    }

    private Rule createRuleFromResultSet(ResultSet rs) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("name"));
        return rule;
    }
}
