package ru.javalab.mongodbwithhateoas.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import ru.javalab.mongodbwithhateoas.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class ReviewRepository implements CrudRepository<Review, String> {
    private final MongoCollection<Document> collection;

    public ReviewRepository(){
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("education");
        collection = database.getCollection("review");
    }

    @Override
    public Review save(Review entity) {
        Document review = new Document();
        review.append("text", entity.getText());
        collection.insertOne(review);
        return entity;
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        collection.find().forEach(document ->{
            reviews.add(Review.builder().text(document.getString("text")).build());
        });
        return reviews;
    }

    @Override
    public void delete(String id) {
        Bson filter = eq("_id", new ObjectId(id));
        collection.deleteOne(filter);
    }

    @Override
    public Optional<Review> findById(String id) {
        Document review = collection.find(new Document("_id", new ObjectId(id))).first();
        return Optional.of(Review.builder().text(review.getString("text")).build());
    }
}
