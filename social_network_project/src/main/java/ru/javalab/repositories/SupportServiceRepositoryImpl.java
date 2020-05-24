package ru.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.javalab.dto.MessageDto;
import ru.javalab.models.entity.SupportTicket;
import ru.javalab.models.entity.User;
import ru.javalab.models.enums.Role;
import ru.javalab.models.enums.State;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SupportServiceRepositoryImpl implements SupportServiceRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    private RowMapper<SupportTicket> supportTicketRowMapper = (row, rowMapper) ->
            SupportTicket.builder()
                    .id(row.getLong("id_of_ticket"))
                    .text(row.getString("message_of_ticket"))
                    .token(row.getString("token_of_ticket"))
                    .userId(row.getLong("user_id_of_ticket"))
                    .user(userRepository.find(row.getLong("user_id_of_post")).get())
                    .build();

    private ResultSetExtractor<List<SupportTicket>> listResultSetExtractor = resultSet -> {
        List<SupportTicket> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(SupportTicket.builder()
                    .id(resultSet.getLong("id_of_ticket"))
                    .text(resultSet.getString("message_of_ticket"))
                    .token(resultSet.getString("token_of_ticket"))
                    .userId(resultSet.getLong("user_id_of_ticket"))
                    .user(User.builder()
                            .id(resultSet.getLong("id_user"))
                            .name(resultSet.getString("name_of_user"))
                            .email(resultSet.getString("email_of_user"))
                            .role(Role.valueOf(resultSet.getString("role_of_user")))
                            .state(State.valueOf(resultSet.getString("state_of_user")))
                            .build())
                    .build());
        }
        return list;
    };

    @Override
    public List<MessageDto> findByTokenUnique() {
        String sql = "SELECT * FROM support_service INNER JOIN person ON user_id_of_ticket = id_user GROUP BY token_of_ticket";
        return MessageDto.from(jdbcTemplate.query(sql, listResultSetExtractor));
    }

    @Override
    public List<SupportTicket> findByUserId(Long userId) {
        String sql = "SELECT * FROM support_service INNER JOIN person ON user_id_of_ticket = id_user WHERE user_id_of_ticket = ? ORDER BY id_of_ticket DESC";
        return jdbcTemplate.query(sql, new Object[]{userId}, listResultSetExtractor);
    }

    @Override
    public List<SupportTicket> findByToken(String token) {
        String sql = "SELECT * FROM support_service INNER JOIN person ON user_id_of_ticket = id_user WHERE token_of_ticket = ? ORDER BY id_of_ticket DESC";
        return jdbcTemplate.query(sql, new Object[]{token}, listResultSetExtractor);
    }

    @Override
    public Optional<SupportTicket> find(Long id) {
        String sql = "SELECT * FROM support_service WHERE id_of_ticket = ?";
        try{
            SupportTicket supportTicket = jdbcTemplate.queryForObject(sql, new Object[]{id}, supportTicketRowMapper);
            return Optional.of(supportTicket);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<SupportTicket> findAll() {
        return null;
    }

    @Override
    public SupportTicket save(SupportTicket entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO support_service(token_of_ticket, user_id_of_ticket, message_of_ticket) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getToken());
            preparedStatement.setLong(2, entity.getUserId());
            preparedStatement.setString(3, entity.getText());
            return preparedStatement;
        }, keyHolder);

        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        entity.setUser(userRepository.find(entity.getUserId()).get());

        return entity;
    }

    @Override
    public void delete(Long aLong) {

    }
}
