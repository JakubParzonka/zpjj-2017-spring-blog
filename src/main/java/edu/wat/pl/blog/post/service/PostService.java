package edu.wat.pl.blog.post.service;

import edu.wat.pl.blog.post.Post;
import edu.wat.pl.blog.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    public void savePost(Post newPost) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        newPost.setCreationDate(dtf.format(LocalDate.now()));
        postRepository.save(newPost);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    // Zakładam unikalność tytułów
    public Post findPostByTitle(String title) {
        return postRepository.findPostByTitle(title).get(0);
    }

}
