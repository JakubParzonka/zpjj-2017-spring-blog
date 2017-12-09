package edu.wat.pl.blog.post.controller;

import edu.wat.pl.blog.comment.Comment;
import edu.wat.pl.blog.comment.CommentService;
import edu.wat.pl.blog.post.model.Post;
import edu.wat.pl.blog.post.service.PostService;
import edu.wat.pl.blog.title.service.TitlesService;
import edu.wat.pl.blog.user.service.UserService;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PostsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TitlesService titlesService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(Post post, HttpSession session) {
        logger.info("Post object has been captured: " + post.toString());
        String id;
        Post postFromSession = (Post) session.getAttribute("post");
        if (postFromSession != null) {
            //edit
            postFromSession.setTitle(post.getTitle());
            postFromSession.setContents(post.getContents());
            postService.savePost(postFromSession);
            id = postFromSession.getId();
        } else {
            postService.savePost(post);
            id = post.getId();
        }
        // dodać flagę czy update czy nie
        return "redirect:/post/" + id;

    }

    @GetMapping(value = "/deletePost/{postId}")
    public String deletePost(Model model, @PathVariable("postId") String id) {
        logger.info("Post to deleted has been captured with id: " + id);
        postService.deletePost(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("title", "Add post");
        model.addAttribute("isAdmin", userService.isCurrentUserAnAdmin(auth));
        return "redirect:/posts";
    }

    @GetMapping(value = "/editPost/{postId}")
    public String editPost(Model model, HttpSession session, @PathVariable("postId") String id) {
        logger.info("Post to update has been captured with id: " + id);
        //  postService.savePost(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Post post = postService.findPostById(id);
        session.setAttribute("post", post);

        model.addAttribute("post", post);
        model.addAttribute("title", "Edit post");
        model.addAttribute("isAdmin", userService.isCurrentUserAnAdmin(auth));
        return "addPost";
    }

    @RequestMapping(value = "/add_post", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("post", new Post());
        //TODO domyślnie tylko admin
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", ((auth == null) ? "unknown" : auth.getName()));
        model.addAttribute("isAdmin", userService.isCurrentUserAnAdmin(auth));
        return "addPost";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String showAllPosts(Model model) {
        List<Post> posts = postService.findAllPosts();
        int postsNumber = posts.size();
        model.addAttribute("posts", posts);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", ((auth == null) ? "unknown" : auth.getName()));
        model.addAttribute("postsNumber", postsNumber);
        model.addAttribute("isInFav", false);
        model.addAttribute("isAdmin", userService.isCurrentUserAnAdmin(auth));
        return "posts";
    }

    @GetMapping("/post/{postId}")
    public String showSpecificPost(Model model, @PathVariable("postId") String id, HttpSession session) {
        Post post = postService.findPostById(id);
        if (post.getComments() == null || post.getComments().isEmpty()) {
            post.setComments(new ArrayList<>());
            post.getComments().add(new Comment("", "", ""));
        }
        model.addAttribute("specificPost", post);
        model.addAttribute("comment", new Comment());
        model.addAttribute("numberOfComments", post.getComments() != null ? post.getComments().size() : 0);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isAdmin", userService.isCurrentUserAnAdmin(auth));
        model.addAttribute("username", ((auth == null) ? "unknown" : auth.getName()));
        session.setAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(Comment comment, HttpSession session) {
        Post post = (Post) session.getAttribute("post");

        logger.info("Post with a comment has been captured.");
        commentService.updatePostWithComment(post, comment);
        return "redirect:/post/" + post.getId();
    }


}
