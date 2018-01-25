package edu.wat.pl.blog.post.service;

import edu.wat.pl.blog.post.model.Post;
import edu.wat.pl.blog.post.repository.PostRepository;
import edu.wat.pl.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void savePost(Post newPost) {
        if (newPost.getCreationDate() == null) newPost.setCreationDate(TimeUtils.getCurrentTime());
        if (newPost.getComments() == null || newPost.getComments().isEmpty()) {
            newPost.setComments(new ArrayList<>());
        }
        postRepository.save(newPost);
    }

    public void deletePost(String id) {
        postRepository.deletePostById(id);
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

}
