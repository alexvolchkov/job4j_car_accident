package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccidentTypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentTypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<AccidentType> findAll() {
        return jdbc.query("select * from accident_type",
                (rs, row) -> createAccidentTypeFromResultSet(rs));
    }

    public Optional<AccidentType> findById(int id) {
        return jdbc.query("select * from accident_type where id = ?",
                new Object[] {id},
                (rs, row) -> createAccidentTypeFromResultSet(rs)).stream().findAny();
    }

    private AccidentType createAccidentTypeFromResultSet(ResultSet rs) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(rs.getInt("id"));
        type.setName(rs.getString("name"));
        return type;
    }
}
