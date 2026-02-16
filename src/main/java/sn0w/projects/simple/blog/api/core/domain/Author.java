package sn0w.projects.simple.blog.api.core.domain;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

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

    public static class AuthorId{
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

    public static class AuthorName{
        private static final int MIN_NAME_LEN = 3;
        private static final int MAX_NAME_LEN = 100;

        private final String value;

        private AuthorName(String value) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Author name can't be null or empty");
            }

            String trimmedValue = value.trim();

            if (trimmedValue.length() <= MIN_NAME_LEN || trimmedValue.length() >= MAX_NAME_LEN) {
                throw new IllegalArgumentException(
                        "Author name must be at least" + MIN_NAME_LEN +
                        "character and can't more than " + MAX_NAME_LEN + "character");
            }

            this.value = trimmedValue;
        }

        public static AuthorName of(String value) {
            return new AuthorName(value);
        }

        public String getValue() {
            return value;
        }
    }
}
