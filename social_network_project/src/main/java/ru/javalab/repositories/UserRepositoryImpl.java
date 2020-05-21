package ru.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.javalab.models.enums.Role;
import ru.javalab.models.enums.State;
import ru.javalab.models.entity.User;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RowMapper<User> userRowMapper = (row, rowMapper) ->
        User.builder()
                .id(row.getLong("id_user"))
                .password(row.getString("password_of_user"))
                .email(row.getString("email_of_user"))
                .name(row.getString("name_of_user"))
                .state(State.valueOf(row.getString("state_of_user")))
                .role(Role.valueOf(row.getString("role_of_user")))
                .build();

    @Override
    public Optional<User> find(Long id) {
        String sql = "SELECT * FROM person WHERE id_user = ?";
        try{
            User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
            return Optional.of(user);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", userRowMapper);
    }

    @Override
    public void save(User entity) {
        if(!findUserByEmail(entity.getEmail()).isPresent()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person(name_of_user, email_of_user, password_of_user, role_of_user, state_of_user) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getEmail());
                preparedStatement.setString(3, bCryptPasswordEncoder.encode(entity.getPassword()));
                preparedStatement.setString(4, String.valueOf(entity.getRole()));
                preparedStatement.setString(5, String.valueOf(entity.getState()));
                return preparedStatement;
            }, keyHolder);

            entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        }
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        String sql = "SELECT * FROM person WHERE email_of_user = ?";
        try{
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, userRowMapper);
            return Optional.of(user);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }

    }

    @Override
    public void updateStateForUser(String email, State state) {
        String sql = "UPDATE person SET state_of_user = ? WHERE email_of_user = ?";
        jdbcTemplate.update(sql, "CONFIRMED", email);
    }
}
