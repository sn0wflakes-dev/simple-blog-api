package sn0w.projects.simple.blog.api.core.domain.blog;

public class BlogTitle {
    private final static int MIN_TITLE_LEN = 2;
    private final static int MAX_TITLE_LEN = 100;

    private final String value;

    private BlogTitle(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Blog title can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.length() < MIN_TITLE_LEN || trimmedValue.length() > MAX_TITLE_LEN) {
            throw new IllegalArgumentException(
                    "Title must be at least" + MIN_TITLE_LEN +
                            "character and can't more than " + MAX_TITLE_LEN + "character");
        }

        this.value = trimmedValue;

    }

    public static BlogTitle of(String value) {
        return new BlogTitle(value);
    }

    public String getValue() {
        return value;
    }
}
