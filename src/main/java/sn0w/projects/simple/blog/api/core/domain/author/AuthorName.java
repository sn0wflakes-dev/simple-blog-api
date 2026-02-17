package sn0w.projects.simple.blog.api.core.domain.author;

public class AuthorName {
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
