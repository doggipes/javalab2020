package ru.javalab.mongodbwithhateoas.repository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.javalab.mongodbwithhateoas.model.Car;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class CarRepository implements CrudRepository<Car, String>{

    private final MongoTemplate mongoTemplate;

    public CarRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Car save(Car entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public List<Car> findAll() {
        return mongoTemplate.findAll(Car.class);
    }

    @Override
    public void delete(String s) {
        mongoTemplate.remove(new Query(where("_id").is(new ObjectId(s))));
    }

    @Override
    public Optional<Car> findById(String s) {
        return Optional.of(mongoTemplate.findById(new ObjectId(s), Car.class));
    }
}
