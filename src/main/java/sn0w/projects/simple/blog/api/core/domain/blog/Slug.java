package sn0w.projects.simple.blog.api.core.domain.blog;

import java.util.regex.Pattern;

public class Slug {
    private final static int MIN_SLUG_LEN = 3;
    private final static int MAX_SLUG_LEN = 200;

    private final static Pattern SLUG_REGEXP = Pattern.compile("^[A-Za-z-]+$");

    private final String value;

    private Slug(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Slug can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.length() < MIN_SLUG_LEN || trimmedValue.length() > MAX_SLUG_LEN) {
            throw new IllegalArgumentException(
                    "Slug must be at least" + MIN_SLUG_LEN +
                            "character and can't more than " + MAX_SLUG_LEN + "character");
        }

        String slug = trimmedValue.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");

        if (!SLUG_REGEXP.matcher(slug).matches()) {
            throw new IllegalArgumentException("Slug contains only alphabet and hyphen");
        }

        this.value = slug;
    }

    public static Slug of(String value) {
        return new Slug(value);
    }

    public String getValue() {
        return value;
    }
}
