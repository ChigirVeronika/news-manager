package com.epam.newsmanager.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * News comment
 */
public class Comment implements Serializable {

    public static final long serialVersionUID = 1;

    /**
     * Unique identifier
     */
    private long commentId;

    /**
     * Unique identifier of news which comment belongs to
     */
    private long newsId;

    /**
     * Comment text
     */
    private String commentText;

    /**
     * Comment creation date
     */
    private Timestamp creationDate;

    public Comment() {
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", newsId=" + newsId +
                ", commentText='" + commentText + '\'' +
                ", creationDate=" + creationDate +
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

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) {
            return false;
        }
        if (newsId != comment.newsId) {
            return false;
        }
        if (commentText != null ? !commentText.equals(comment.commentText) : comment.commentText != null) {
            return false;
        }
        return !(creationDate != null ? !creationDate.equals(comment.creationDate) : comment.creationDate != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (int) (newsId ^ (newsId >>> 32));
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
