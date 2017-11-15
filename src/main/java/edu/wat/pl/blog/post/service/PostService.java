package edu.wat.pl.blog.post.service;

import edu.wat.pl.blog.comment.Comment;
import edu.wat.pl.blog.post.model.Post;
import edu.wat.pl.blog.post.repository.PostRepository;
import edu.wat.pl.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PostRepository postRepository;

    public void savePost(Post newPost) {
        newPost.setCreationDate(TimeUtils.getCurrentTime());
        if(newPost.getComments() == null || newPost.getComments().isEmpty()) {
            newPost.setComments(new ArrayList<>());
            newPost.getComments().add(new Comment("","",""));
        }
        postRepository.save(newPost);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    // Zakładam unikalność tytułów
    public Post findPostByTitle(String title) {
        return postRepository.findPostByTitle(title).get(0);
    }

    public Post findPostById(String id) {
        return postRepository.findPostById(id).get(0);
    }

    public void updatePostWithComment(Post postToUpdate) {
        postToUpdate.getComments().get(postToUpdate.getComments().size() - 1).setCommentsDate(TimeUtils.getCurrentTime());

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(postToUpdate.getId()));
        Update update = new Update();
        update.set("comments", postToUpdate.getComments());
        mongoTemplate.upsert(query, update, Post.class);
    }
}
