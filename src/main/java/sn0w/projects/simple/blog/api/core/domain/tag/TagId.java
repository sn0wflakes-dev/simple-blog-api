package sn0w.projects.simple.blog.api.core.domain.tag;

import java.util.UUID;

public class TagId {
    private final String value;

    public TagId(String value) {
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException("Tag id can't be null or empty");
        }
        this.value = value;
    }

    public static TagId generate() {
        return new TagId(UUID.randomUUID().toString());
    }

    public static TagId of(String value) {
        return new TagId(value);
    }

    public String getValue() {
        return value;
    }
}
