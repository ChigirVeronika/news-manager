package com.epam.newsmanager.entity.dto;

import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.entity.Tag;

import java.util.Set;

/**
 * DTO class for news searching.
 */
public class SearchNewsDto {
    private Set<Tag> tags;
    private Author author;

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
