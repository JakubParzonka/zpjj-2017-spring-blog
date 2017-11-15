package edu.wat.pl.blog.comment;


public class Comment {

    private String username;

    private String commentsContent;

    private String commentsDate;

    public Comment() {
    }

    public Comment(String username, String commentsContent, String commentsDate) {
        this.username = username;
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
                ", commentsContent='" + commentsContent + '\'' +
                ", commentsDate='" + commentsDate + '\'' +
                '}';
    }
}
