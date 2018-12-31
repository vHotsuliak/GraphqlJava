package com.howtographql.hackernews;

import lombok.Getter;

@Getter
public class Link {

    private final String id; //the new field
    private final String url;
    private final String description;

    public Link(String url, String description) {
        this(null, url, description);
    }

    public Link(String id, String url, String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }
}
