package edu.wat.pl.blog.favorite.model;

public class Favorite {

    private String postId;

    public Favorite(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

}
