package sn0w.projects.simple.blog.api.core.domain.blog;

import java.util.UUID;

public record BlogId(String blogId) {
    public BlogId {
        if (blogId == null || blogId.isBlank()) {
            throw new IllegalArgumentException("Blog id can't be null or empty");
        }

    }

    public static BlogId generate() {
        return new BlogId(UUID.randomUUID().toString());
    }
}
