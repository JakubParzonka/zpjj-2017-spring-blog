package edu.wat.pl.blog.user.repository;

import edu.wat.pl.blog.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

}
