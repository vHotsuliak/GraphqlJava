package com.howtographql.hackernews;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

public class UserRepository {
    private final MongoCollection<Document> users;

    public UserRepository(MongoCollection<Document> users) {
        this.users = users;
    }

    public List<User> getAllUsers(UserFilter filter) {
        Optional<Bson> mongoFilter = Optional.ofNullable(filter).map(this::buildFilter);

        List<User> allUsers = new ArrayList<>();
        for (Document doc : mongoFilter.map(users::find).orElseGet(users::find)) {
            allUsers.add(new User(
                    doc.get("_id").toString(),
                    doc.getString("name"),
                    doc.getString("email"),
                    doc.getString("password")));
        }
        return allUsers;
    }


    public User findByEmail(String email) {
        Document doc = users.find(eq("email", email)).first();
        return user(doc);
    }

    public User findById(String id) {
        Document doc = users.find(eq("_id", new ObjectId(id))).first();
        return user(doc);
    }

    public User saveUser(User user) {
        Document doc = new Document();
        doc.append("name", user.getName());
        doc.append("email", user.getEmail());
        doc.append("password", user.getPassword());
        users.insertOne(doc);
        return new User(
                doc.get("_id").toString(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }

    private User user(Document doc) {
        if (doc == null) {
            return null;
        }
        return new User(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("email"),
                doc.getString("password"));
    }

    private Bson buildFilter(UserFilter filter) {
        String emailPattern = filter.getEmailContains();
        String namePattern = filter.getNameContains();
        String passwordPattern = filter.getPasswordContains();
        Bson emailCondition =
                emailPattern != null && !emailPattern.isEmpty() ? regex("email", ".*" + emailPattern + ".*", "i") : null;
        Bson nameCondition =
                namePattern != null && !namePattern.isEmpty() ? regex("name", ".*" + namePattern + ".*", "i") : null;
        Bson passwordCondition =
                passwordPattern != null && !passwordPattern.isEmpty() ? regex("password", ".*" + passwordPattern + ".*", "i") : null;
        Iterable<Bson> bsonIterable = Arrays.asList(emailCondition, nameCondition, passwordCondition)
                .stream().filter(i -> i != null).collect(Collectors.toList());
        return and(bsonIterable);
    }
}
