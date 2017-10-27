package edu.wat.pl.blog.post.controller;

import edu.wat.pl.blog.post.Post;
import edu.wat.pl.blog.post.service.PostService;
import edu.wat.pl.blog.title.TitlesService;
import edu.wat.pl.blog.title.dto.Titles;
import edu.wat.pl.blog.title.repository.TitlesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PostsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TitlesService titlesService;
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(Post post) {
        logger.info("Post object has been captured: " + post.toString());
        postService.savePost(post);
        titlesService.saveTitle(post);
        return "redirect:/";
    }

    @RequestMapping(value = "/add_post", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("post", new Post());
        return "addPost";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String showAllPosts(Model model){
        return "posts";
    }

}
