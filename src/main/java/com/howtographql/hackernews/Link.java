package com.howtographql.hackernews;

import lombok.Getter;

@Getter
public class Link {

    private final String id;
    private final String url;
    private final String description;
    private final String userId;

    public Link(String url, String description, String userId) {
        this(null, url, description, userId);
    }

    public Link(String id, String url, String description, String userId) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.userId = userId;
    }
}
