package com.howtographql.hackernews;

import lombok.Getter;

@Getter
public class Link {
    private final String url;
    private final String description;

    public Link(String url, String description) {
        this.url = url;
        this.description = description;
    }
}
