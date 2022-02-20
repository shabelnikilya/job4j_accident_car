package ru.job4j.accident.store;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate implements Store {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbc.query("SELECT id, name, text, address, type_id "
                        + "FROM accident",
                (rs, row) -> Accident.of(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        findTypeById(rs.getInt("type_id")),
                        allRulesAccident(rs.getInt("id"))
                ));
    }

    @Override
    public Accident findById(int id) {
        return jdbc.queryForObject("SELECT id, name, text, address, type_id "
                        + "FROM accident WHERE id = ?",
                (rs, row) -> Accident.of(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        findTypeById(rs.getInt("type_id")),
                        allRulesAccident(rs.getInt("id"))
                ), id);
    }

    @Override
    public void addOrUpdate(Accident accident) {
        if (accident.getId() == 0) {
            saveAccident(accident);
        } else {
            updateAccident(accident);
        }
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM accident WHERE id = ?", id);
        jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ?", id);
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return jdbc.query("SELECT id, name FROM type",
                (rs, row) -> AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("name")
                        )
                );
    }

    @Override
    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("SELECT id, name FROM type WHERE id = ?",
                (rs, row) -> AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ),
                id);
    }

    @Override
    public Collection<Rule> findAllRules() {
        return jdbc.query("SELECT id, name FROM rule",
                (rs, row) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                )
        );
    }

    @Override
    public Rule findRuleById(int id) {
        return jdbc.queryForObject("SELECT id, name FROM rule WHERE id = ?",
                (rs, row) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ),
                id);
    }

    @Override
    public Set<Rule> findRulesByIds(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String id : ids) {
            rsl.add(this.findRuleById(Integer.parseInt(id)));
        }
        return rsl;
    }

    public Set<Rule> findRulesByIds(Set<String> ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String id : ids) {
            rsl.add(this.findRuleById(Integer.parseInt(id)));
        }
        return rsl;
    }

    private List<String> findRulesByIdAccident(int id) {
        return jdbc.query("SELECT rule_id FROM accidents_rules WHERE accident_id = ?",
                (rs, row) -> String.valueOf(rs.getInt("rule_id")), id);
    }

    private Set<Rule> allRulesAccident(int id) {
        return findRulesByIds(new HashSet<>(findRulesByIdAccident(id)));
    }

    private void saveAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO accident (name, text, address, type_id) VALUES (?, ?, ?, ?)",
                            new String[]{"id"}
                    );
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                },
                keyHolder
        );
        saveRulesInAccident(accident, keyHolder.getKey().intValue());
    }

    private void updateAccident(Accident accident) {
        jdbc.update("UPDATE accident SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ?", accident.getId());
        saveRulesInAccident(accident, accident.getId());
    }

    private void saveRulesInAccident(Accident accident, int id) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accidents_rules(accident_id, rule_id) VALUES (?, ?);",
                    id,
                    rule.getId());
        }
    }
}
