package edu.wat.pl.blog.comment;


public class Comment {

    private String username;

    public String ownerPostId;

    public String commentId;

    private String commentsContent;

    private String commentsDate;

    public Comment() {
    }

    public Comment(String username, String ownerPostId, String commentId, String commentsContent, String commentsDate) {
        this.username = username;
        this.ownerPostId = ownerPostId;
        this.commentId = commentId;
        this.commentsContent = commentsContent;
        this.commentsDate = commentsDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentsContent() {
        return commentsContent;
    }

    public void setCommentsContent(String commentsContent) {
        this.commentsContent = commentsContent;
    }

    public String getCommentsDate() {
        return commentsDate;
    }

    public void setCommentsDate(String commentsDate) {
        this.commentsDate = commentsDate;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "username='" + username + '\'' +
                ", ownerPostId='" + ownerPostId + '\'' +
                ", commentId='" + commentId + '\'' +
                ", commentsContent='" + commentsContent + '\'' +
                ", commentsDate='" + commentsDate + '\'' +
                '}';
    }

    public String getOwnerPostId() {
        return ownerPostId;
    }

    public void setOwnerPostId(String ownerPostId) {
        this.ownerPostId = ownerPostId;
    }

    public String getcommentId() {
        return commentId;
    }

    public void setcommentId(String commentId) {
        this.commentId = commentId;
    }
}
