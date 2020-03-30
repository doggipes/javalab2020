package ru.javalab.firstspringproject.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.javalab.firstspringproject.models.Course;
import ru.javalab.firstspringproject.models.Lesson;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

public class CourseRepositoryImpl implements CourseRepository{
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Course> courseRowMapper = (row, rowMapper) ->
            Course.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .build();

    private ResultSetExtractor<List<Course>> courseResultSetExtractor = resultSet -> {
        Map<Long, Course> listMap = new HashMap<>();
      while(resultSet.next()){
          listMap.putIfAbsent(resultSet.getLong("course.id"), Course.builder()
                  .id(resultSet.getLong("course.id"))
                  .name(resultSet.getString("course.name"))
                  .lessons(new ArrayList<>())
                  .build());
          listMap.get(resultSet.getLong("course.id")).getLessons().add(Lesson.builder()
                                                                                            .id(resultSet.getLong("lesson.id"))
                                                                                            .name(resultSet.getString("lesson.name"))
                                                                                            .course_id(resultSet.getLong("course.id"))
                                                                                            .build());
      }
      return new ArrayList<>(listMap.values());
    };


    public CourseRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Course> find(Long aLong) {
        String sql = "SELECT * FROM course WHERE id = ?";
        Course course = jdbcTemplate.queryForObject(sql, new Object[]{aLong}, courseRowMapper);
        return Optional.ofNullable(course);
    }

    @Override
    public List<Course> findAll() {
        List<Course> course = jdbcTemplate.query("SELECT * FROM informatics.course INNER JOIN lesson ON course.id = lesson.course_id", courseResultSetExtractor);
        return course;
    }

    @Override
    public void save(Course entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO course(name) values (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            return preparedStatement;
        }, keyHolder);

        entity.setId((Long) keyHolder.getKey());
    }

    @Override
    public void delete(Long aLong) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM course WHERE id = ?");
            preparedStatement.setLong(1, aLong);
            return preparedStatement;
        });
    }
}
