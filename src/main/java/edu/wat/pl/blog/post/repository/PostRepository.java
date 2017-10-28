package edu.wat.pl.blog.post.repository;

import edu.wat.pl.blog.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>/*, QueryDslPredicateExecutor<Post> */ {

    List<Post> findPostByTitle(String s);

    List<Post> findPostById(String s);


}
