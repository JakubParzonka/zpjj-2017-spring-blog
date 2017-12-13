package edu.wat.pl.blog.favorite.service;

import edu.wat.pl.blog.favorite.model.Favorite;
import edu.wat.pl.blog.post.model.Post;
import edu.wat.pl.blog.post.service.PostService;
import edu.wat.pl.blog.user.model.User;
import edu.wat.pl.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public List<Post> getFavPosts(User user) {
        List<String> postsId = getFavPostsIdForSpecificUser(user);
        return postsId.stream().map(postId -> postService.findPostById(postId)).collect(Collectors.toList());
    }

    private List<String> getFavPostsIdForSpecificUser(User user) {
        //username is getting from context, so can not be null
        List<String> postsId = user.getFavorites().stream().map(Favorite::getPostId).collect(Collectors.toList());

        if (postsId.isEmpty()) System.out.println("Found 0 favorite posts for " + user.getUsername());
        else System.out.println("Found " + postsId.size() + " favorite posts for " + user.getUsername());

        return postsId;
    }

    public void deletePostFromFavorities(User user, String postId) {
        Set<Favorite> favPost = user.getFavorites();
        Favorite fav = favPost.stream().filter(favorite -> Objects.equals(favorite.getPostId(), postId)).findFirst().get();
        user.getFavorites().remove(fav);
        userService.saveUser(user);
    }

    public void addPostToFavorities(User user, String postId) {
        if (user.getFavorites() == null || user.getFavorites().isEmpty()) {
            System.out.println("No farovites posts for " + user.getUsername());
            user.setFavorites(new HashSet<>());
            user.getFavorites().add(new Favorite(postId));
        } else {
            if (user.getFavorites().stream().noneMatch(favorite -> Objects.equals(favorite.getPostId(), postId)))
                user.getFavorites().add(new Favorite(postId));
        }
        System.out.println("Post " + postId + " added to " + user.getUsername() + " favorites list");
        userService.saveUser(user);
    }
}
