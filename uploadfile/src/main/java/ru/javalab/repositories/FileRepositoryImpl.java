package ru.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.javalab.models.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class FileRepositoryImpl implements FileRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<FileModel> fileRowMapper = (row, rowMapper) ->
            FileModel.builder()
                    .id(BigInteger.valueOf(row.getLong("id")))
                    .name(row.getString("name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .path(row.getString("path"))
                    .build();

    @Override
    public Optional<FileModel> findFileByName(String name) {
        String sql = "SELECT * FROM file WHERE name = ?";
        try{
            FileModel fileModel = jdbcTemplate.queryForObject(sql, new Object[]{name}, fileRowMapper);
            return Optional.ofNullable(fileModel);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<FileModel> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<FileModel> findAll() {
        return null;
    }

    @Override
    public void save(FileModel entity) {
        if(!findFileByName(entity.getName()).isPresent()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO file(name, size, type, path) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setLong(2, entity.getSize());
                preparedStatement.setString(3, entity.getType());
                preparedStatement.setString(4, entity.getPath());
                return preparedStatement;
            }, keyHolder);

            entity.setId((BigInteger) keyHolder.getKey());
        }
    }

    @Override
    public void delete(Long aLong) {

    }
}
