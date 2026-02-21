package sn0w.projects.simple.blog.api.core.domain.tag;

public class TagName {
    private final static int MIN_TAG_LEN = 2;
    private final static int MAX_TAG_LEN = 100;

    private final String value;

    public TagName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Tag name can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.length() <= MIN_TAG_LEN || trimmedValue.length() >= MAX_TAG_LEN) {
            throw new IllegalArgumentException(
                    "Tag name must be at least" + MIN_TAG_LEN +
                            "character and can't more than " + MAX_TAG_LEN + "character");
        }

        this.value = trimmedValue.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");
    }

    public static TagName of(String name) {
        return new TagName(name);
    }

    public String getValue() {
        return value;
    }
}
