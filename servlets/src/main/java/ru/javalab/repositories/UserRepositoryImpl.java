package ru.javalab.repositories;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.javalab.models.Role;
import ru.javalab.models.State;
import ru.javalab.models.User;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RowMapper<User> userRowMapper = (row, rowMapper) ->
        User.builder()
                .id(BigInteger.valueOf(row.getLong("id")))
                .password(row.getString("password"))
                .email(row.getString("email"))
                .name(row.getString("name"))
                .state(State.valueOf(row.getString("state")))
                .role(Role.valueOf(row.getString("role")))
                .build();

    @Override
    public Optional<User> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {
        if(!findUserByEmail(entity.getEmail()).isPresent()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(name, email, password, state, role) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getEmail());
                preparedStatement.setString(3, bCryptPasswordEncoder.encode(entity.getPassword()));
                preparedStatement.setString(4, String.valueOf(entity.getState()));
                preparedStatement.setString(5, String.valueOf(entity.getRole()));
                return preparedStatement;
            }, keyHolder);

            entity.setId((BigInteger) keyHolder.getKey());
        }


    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try{
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }

    }
}
