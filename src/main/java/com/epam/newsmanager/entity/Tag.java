package com.epam.newsmanager.entity;

import java.io.Serializable;

/**
 * News tag
 */
public class Tag implements Serializable {

    public static final long serialVersionUID = 1;

    /**
     * Unique identifier
     */
    private long tagId;

    /**
     * Tag name
     */
    private String tagName;

    public Tag() {
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
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

        Tag tag = (Tag) o;

        if (tagId != tag.tagId) {
            return false;
        }
        return !(tagName != null ? !tagName.equals(tag.tagName) : tag.tagName != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }
}
