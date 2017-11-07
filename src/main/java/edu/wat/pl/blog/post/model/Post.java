package edu.wat.pl.blog.post.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "post")
public class Post {

    @Id
    private String id;

    private String title;

    private String contents;

    private String creationDate;

//    private List<String> tags;
//
//    private String wrongTags;

    public Post() {

    }

    public Post(String id, String title, String contents, String creationDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.creationDate = creationDate;
//        this.tags = tags;
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

//    public String getWrongTags() {
//        return wrongTags;
//    }
//
//    public void setWrongTags(String wrongTags) {
//        this.wrongTags = wrongTags;
//    }
//
//    public List<String> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<String> tags) {
//        this.tags = tags;
//    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
