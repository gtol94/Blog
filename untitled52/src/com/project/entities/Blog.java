package com.project.entities;

import java.util.Date;

public class Blog {
    private Long id;
    private User author;
    private String title;
    private String shortContent;
    private String content;
    private Date postDate;

    public Blog(Long id, User author, String title, String shortContent, String content, Date postDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.shortContent = shortContent;
        this.content = content;
        this.postDate = postDate;
    }

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
