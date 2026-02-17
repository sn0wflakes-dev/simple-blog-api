package sn0w.projects.simple.blog.api.core.domain.author;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Author {
    private final AuthorId authorId;
    private AuthorName authorName;

    @Getter
    private final OffsetDateTime createdAt;

    @Getter
    private OffsetDateTime updatedAt;

    private Author(AuthorId authorId, AuthorName authorName) {
        this.authorId = Objects.requireNonNull(authorId);
        this.authorName = Objects.requireNonNull(authorName);
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public static Author create(String name) {
        return new Author(
                AuthorId.generate(),
                AuthorName.of(name)
        );
    }

    public void updateAuthor(String authorName) {
        this.authorName = AuthorName.of(authorName);
        this.updatedAt = OffsetDateTime.now();
    }

    public AuthorId getAuthorId() {
        return authorId;
    }

    public AuthorName getAuthorName() {
        return authorName;
    }
}
