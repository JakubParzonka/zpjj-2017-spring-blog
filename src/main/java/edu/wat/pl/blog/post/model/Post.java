package edu.wat.pl.blog.post.model;

import edu.wat.pl.blog.comment.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "post")
public class Post {

    @Id
    private String id;

    private String title;

    private String contents;

    private String creationDate;

    private ArrayList<Comment> comments;

    public Post(String id, String title, String contents, String creationDate, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.creationDate = creationDate;
        this.comments = comments;
    }

    public Post() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", creationDate='" + creationDate + '}';
    }
}
