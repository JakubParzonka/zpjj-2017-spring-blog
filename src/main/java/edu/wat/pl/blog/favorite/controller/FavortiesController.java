package edu.wat.pl.blog.favorite.controller;

import edu.wat.pl.blog.favorite.service.FavoriteService;
import edu.wat.pl.blog.post.model.Post;
import edu.wat.pl.blog.user.model.User;
import edu.wat.pl.blog.user.service.UserService;
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
import java.util.List;


@Controller
public class FavortiesController {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping(value = "/addToFavorites/{postId}")
    public String addToFavorites(Model model, @PathVariable("postId") String id) {
        User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        favoriteService.addPostToFavorities(user, id);
        model.addAttribute("isInFav", true);
        return "redirect:/post/" + id;
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String showFavoritesPosts(Model model, HttpSession session) {
        User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Post> favPosts = favoriteService.getFavPosts(user);
        int postsNumber = favPosts.size();
        model.addAttribute("posts", favPosts);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", ((auth == null) ? "unknown" : auth.getName()));
        model.addAttribute("postsNumber", postsNumber);
        model.addAttribute("isInFav", true);
        model.addAttribute("isAdmin", userService.isCurrentUserAnAdmin(auth));
        return "posts";
    }

    @GetMapping(value = "/removeFromFav/{postId}")
    public String deletePost(Model model, @PathVariable("postId") String id) {
        User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        favoriteService.deletePostFromFavorities(user, id);
        return "redirect:/favorites";
    }
}
