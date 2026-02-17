package sn0w.projects.simple.blog.api.core.domain.Blog;

public class BlogContent {
    private final static int MIN_CONTENT_LEN = 2;

    private final String value;

    private BlogContent(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Blog content can't be null or empty");
        }

        this.value = value;
    }

    public static BlogContent of(String value) {
        return new BlogContent(value);
    }

    public String getValue() {
        return value;
    }
}
