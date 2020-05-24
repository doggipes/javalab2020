package ru.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.javalab.models.entity.Post;
import ru.javalab.models.entity.User;
import ru.javalab.models.enums.Role;
import ru.javalab.models.enums.State;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Component
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    private RowMapper<Post> postRowMapper = (row, rowMapper) ->
            Post.builder()
                    .id(row.getLong("id_post"))
                    .text(row.getString("text_of_post"))
                    .image(row.getString("image_of_post"))
                    .userId(row.getLong("user_id_of_post"))
                    .user(userRepository.find(row.getLong("user_id_of_post")).get())
                    .build();

    private ResultSetExtractor<List<Post>> listResultSetExtractor = resultSet -> {
        List<Post> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(Post.builder()
                    .id(resultSet.getLong("id_post"))
                    .text(resultSet.getString("text_of_post"))
                    .image(resultSet.getString("image_of_post"))
                    .userId(resultSet.getLong("user_id_of_post"))
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
    public Optional<Post> find(Long id) {
        String sql = "SELECT * FROM post WHERE id_post = ?";
        try{
            Post post = jdbcTemplate.queryForObject(sql, new Object[]{id}, postRowMapper);
            return Optional.of(post);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM informatics.post INNER JOIN person ON user_id_of_post = id_user", listResultSetExtractor);
    }

    @Override
    public List<Post> findPostsByUser(User user) {
        String sql = "SELECT * FROM post WHERE user_id_of_post = ?";
        return jdbcTemplate.query(sql, new Object[]{user.getId()}, listResultSetExtractor);
    }

    @Override
    public Post save(Post entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO post(text_of_post, image_of_post, user_id_of_post) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getText());
            preparedStatement.setString(2, entity.getImage());
            preparedStatement.setLong(3, entity.getUserId());
            return preparedStatement;
        }, keyHolder);

        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return entity;
    }

    @Override
    public void delete(Long aLong) {

    }
}
