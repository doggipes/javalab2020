package ru.javalab.rabbitmq.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.javalab.rabbitmq.model.entity.File;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class FileRepositoryImpl implements FileRepository {
    private final JdbcTemplate jdbcTemplate;

    public FileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public File save(File entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO passport(name_of_passport, name_with_extension_of_passport, type_of_passport, size_of_passport) values (?, ?, ?, ?)", new String[]{"id_of_passport"});
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getNameWithExtension());
            preparedStatement.setString(3, entity.getType());
            preparedStatement.setLong(4, entity.getSize());
            return preparedStatement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());

        return entity;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<File> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<File> findAll() {
        return null;
    }
}
