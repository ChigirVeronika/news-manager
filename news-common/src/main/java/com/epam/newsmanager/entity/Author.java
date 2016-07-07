package com.epam.newsmanager.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * News author
 */
public class Author implements Serializable {

    public static final long serialVersionUID = 1;

    /**
     * Unique identifier
     */
    private long authorId;

    /**
     * Name of an author
     */
    private String authorName;

    /**
     * This is a date since which author are not displayable in author dropdown,
     * but still visible on new list and view/update news
     */
    private Timestamp expired;

    public Author() {
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", expired=" + expired +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Author author = (Author) o;

        if (authorId != author.authorId) {
            return false;
        }
        if (authorName != null ? !authorName.equals(author.authorName) : author.authorName != null) {
            return false;
        }
        return !(expired != null ? !expired.equals(author.expired) : author.expired != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (expired != null ? expired.hashCode() : 0);
        return result;
    }
}
