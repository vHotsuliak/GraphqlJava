package com.howtographql.hackernews;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFilter {
    private String nameContains;
    private String emailContains;
    private String passwordContains;

    @JsonProperty("name_contains") //the name must match the schema
    public String getNameContains() {
        return nameContains;
    }

    public void setNameContains(String nameContains) {
        this.nameContains = nameContains;
    }

    @JsonProperty("email_contains")
    public String getEmailContains() {
        return emailContains;
    }

    public void setEmailContains(String emailContains) {
        this.emailContains = emailContains;
    }

    @JsonProperty("password_contains")
    public String getPasswordContains() {
        return passwordContains;
    }

    public void setPasswordContains(String passwordContains) {
        this.passwordContains = passwordContains;
    }
}
