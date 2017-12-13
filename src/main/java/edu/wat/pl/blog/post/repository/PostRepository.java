package edu.wat.pl.blog.post.repository;

import edu.wat.pl.blog.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findPostByTitle(String s);

    List<Post> findPostById(String s);

    void deletePostById(String id);

}
