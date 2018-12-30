package com.howtographql.hackernews;

import lombok.Data;

@Data
public class Link {
    private final String url;
    private final String description;
}
