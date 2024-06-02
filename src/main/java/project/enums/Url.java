package project.enums;

import lombok.Getter;

@Getter
public enum Url {
    HOMEPAGE("https://systeme.io/blog/make-money-home");

    Url(String url) {
        this.url = url;
    }

    private final String url;
}
