package edu.wat.pl.blog.post;

import java.util.List;

public class Post {

    private Integer id;

    private String title;

    private String contents;

    private String creationDate;

    private List<String> tags;

    private String wrongTags;

    public Post() {

    }

    public Post(Integer id, String title, String contents, String creationDate, List<String> tags) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.creationDate = creationDate;
        this.tags = tags;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWrongTags() {
        return wrongTags;
    }

    public void setWrongTags(String wrongTags) {
        this.wrongTags = wrongTags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", tags=" + tags +
                ", wrongTags='" + wrongTags + '\'' +
                '}';
    }
}
