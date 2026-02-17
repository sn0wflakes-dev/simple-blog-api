package sn0w.projects.simple.blog.api.core.domain.blog;

public record Tag(String value) {
    private final static int MIN_TAG_LEN = 2;
    private final static int MAX_TAG_LEN = 100;

    public Tag(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Tag can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.length() < MIN_TAG_LEN || trimmedValue.length() > MAX_TAG_LEN) {
            throw new IllegalArgumentException(
                    "Tag must be at least" + MIN_TAG_LEN +
                            "character and can't more than " + MAX_TAG_LEN + "character");
        }

        this.value = trimmedValue;
    }

    public static Tag of(String value) {
        return new Tag(value);
    }
}
