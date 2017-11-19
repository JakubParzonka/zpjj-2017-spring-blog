package edu.wat.pl.blog.post.controller;

import edu.wat.pl.blog.comment.Comment;
import edu.wat.pl.blog.post.model.Post;
import edu.wat.pl.blog.post.service.PostService;
import edu.wat.pl.blog.title.service.TitlesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;


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
    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    public String deletePost(Post post) {
        logger.info("Post to be deleted has been captured: " + post.toString());
        postService.deletePost(post);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/add_post", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("post", new Post());
        //TODO domyślnie tylko admin
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", ((auth == null) ? "unknown" : auth.getName()));
        return "addPost";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String showAllPosts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", ((auth == null) ? "unknown" : auth.getName()));
        return "posts";
    }

    @GetMapping("/post/{postId}")
    public String showSpecificPost(Model model, @PathVariable("postId") String id) {
        Post post = postService.findPostById(id);
        if (post.getComments() == null || post.getComments().isEmpty()) {
            post.setComments(new ArrayList<>());
            post.getComments().add(new Comment("", "", ""));
        }
        model.addAttribute("specificPost", post);
        model.addAttribute("numberOfComments", post.getComments() != null ? post.getComments().size() : 0);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", ((auth == null) ? "unknown" : auth.getName()));
        return "post";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(Post post) {
        logger.info("Post with a comment has been captured: " + post.getComments().toString());
        postService.updatePostWithComment(post);
        return "redirect:/";
    }


}
