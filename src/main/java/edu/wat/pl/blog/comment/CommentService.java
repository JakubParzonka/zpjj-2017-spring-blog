package edu.wat.pl.blog.comment;

import edu.wat.pl.blog.post.model.Post;
import edu.wat.pl.blog.post.service.PostService;
import edu.wat.pl.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private PostService postService;

    public void updatePostWithComment(Post postToUpdate, Comment comment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        comment.setUsername((auth == null) ? "unknown" : auth.getName());
        comment.setCommentsDate(TimeUtils.getCurrentTime());
        postToUpdate.getComments().add(comment);
        postService.savePost(postToUpdate);
    }
}
