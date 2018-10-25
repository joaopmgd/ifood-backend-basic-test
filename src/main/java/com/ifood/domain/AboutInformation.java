package com.ifood.domain;

import java.util.Date;

public class AboutInformation {

    private String author;
    private String date;
    private String email;
    private String url;
    private String message;

    public AboutInformation() {
        this.author = "João Pedro Mota Gonçalves Dias";
        this.date = new Date().toString();
        this.email = "joaopmgd.gmail.com";
        this.url = "https://github.com/joaopmgd";
        this.message = "The service is Healthy.";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    @Override
    public String toString() {
        return "AboutInformation{" +
                "author='" + author + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
