package sn0w.projects.simple.blog.api.core.domain;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

public class Blog {
    private final BlogId blogId;
    private final String authorName;
    private final Set<Tag> tags;
    private final BlogTitle title;
    private final Slug slug;
    private final BlogContent content;
    private final BlogStatus status;
    private final OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    private Blog(
            BlogId blogId,
            String author,
            Set<Tag> tags,
            BlogTitle title,
            Slug slug,
            BlogContent content,
            BlogStatus status) {
        this.blogId = Objects.requireNonNull(blogId);
        this.authorName = Objects.requireNonNull(author);
        this.tags = Objects.requireNonNull(tags);
        this.title = Objects.requireNonNull(title);
        this.slug = Objects.requireNonNull(slug);
        this.content = Objects.requireNonNull(content);
        this.status = Objects.requireNonNull(status);
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = createdAt;
    }

    public static Blog create(
            String authorName,
            Set<Tag> tags,
            String title,
            String slug,
            String blogContent) {
        return new Blog(
                BlogId.generate(),
                authorName,
                tags,
                BlogTitle.of(title),
                Slug.of(slug),
                BlogContent.of(blogContent),
                BlogStatus.DRAFT
        );
    }

    public void addTag(Tag tag) {
        Objects.requireNonNull(tag);

        if (this.tags.size() > 10) {
            throw new IllegalArgumentException("Tags can't be more than 10 item");
        }

        if (this.tags.contains(tag)) {
            throw new IllegalArgumentException("Tags already exist");
        }

        this.tags.add(tag);
        this.updatedAt = OffsetDateTime.now();

    }

    public static class BlogId{
        private final String blogId;

        public BlogId(String blogId) {
            if (blogId == null || blogId.isBlank()) {
                throw new IllegalArgumentException("Blog id can't be null or empty");
            }

            this.blogId = blogId;
        }

        public static BlogId generate() {
            return new BlogId(UUID.randomUUID().toString());
        }

        public String getBlogId() {
            return blogId;
        }
    }

    public static class Tag{
        private final static int MIN_TAG_LEN = 2;
        private final static int MAX_TAG_LEN = 100;
        private final String value;

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

        public String getValue() {
            return value;
        }
    }

    public static class Slug{
        private final static int MIN_SLUG_LEN = 3;
        private final static int MAX_SLUG_LEN = 200;

        private final static Pattern SLUG_REGEXP = Pattern.compile("^[A-Za-z-]+$");

        private final String value;

        private Slug(String value) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Slug can't be null or empty");
            }

            if (SLUG_REGEXP.matcher(value).matches()) {
                throw new IllegalArgumentException("Slug contains only alphabet and hyphen");
            }

            String trimmedValue = value.trim();

            if (trimmedValue.length() < MIN_SLUG_LEN || trimmedValue.length() > MAX_SLUG_LEN) {
                throw new IllegalArgumentException(
                        "Slug must be at least" + MIN_SLUG_LEN +
                                "character and can't more than " + MAX_SLUG_LEN + "character");
            }

            this.value = trimmedValue;
        }

        public static Slug of(String value) {
            return new Slug(value);
        }

        public String getValue() {
            return value;
        }
    }

    public static class BlogTitle{
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

    public static class BlogContent{
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

    public enum BlogStatus{
        DRAFT,
        PUBLISHED,
        DELETED
    }
}
