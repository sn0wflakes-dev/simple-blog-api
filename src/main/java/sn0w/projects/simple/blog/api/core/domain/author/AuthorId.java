package sn0w.projects.simple.blog.api.core.domain.author;

import java.util.UUID;

public class AuthorId {
    private final String value;

    private AuthorId(String value) {
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException("Author id can't be null or empty");
        }
        this.value = value;
    }

    public static AuthorId generate() {
        return new AuthorId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }
}
