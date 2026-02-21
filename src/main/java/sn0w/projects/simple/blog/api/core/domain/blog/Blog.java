package sn0w.projects.simple.blog.api.core.domain.blog;

import sn0w.projects.simple.blog.api.core.domain.tag.Tag;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

public class Blog {
    private final BlogId blogId;
    private final String authorId;
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
        this.authorId = Objects.requireNonNull(author);
        this.tags = Objects.requireNonNull(tags);
        this.title = Objects.requireNonNull(title);
        this.slug = Objects.requireNonNull(slug);
        this.content = Objects.requireNonNull(content);
        this.status = Objects.requireNonNull(status);
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = createdAt;
    }

    private Blog(
            BlogId blogId,
            String authorId,
            Set<Tag> tags,
            BlogTitle title,
            Slug slug,
            BlogStatus status,
            BlogContent content,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {
        this.blogId = Objects.requireNonNull(blogId);
        this.authorId = Objects.requireNonNull(authorId);
        this.tags = Objects.requireNonNull(tags);
        this.title = Objects.requireNonNull(title);
        this.slug = Objects.requireNonNull(slug);
        this.content = Objects.requireNonNull(content);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        this.status = Objects.requireNonNull(status);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static Blog create(
            String authorId,
            Set<Tag> tags,
            String title,
            String blogContent) {
        return new Blog(
                BlogId.generate(),
                authorId,
                tags,
                BlogTitle.of(title),
                Slug.of(title),
                BlogContent.of(blogContent),
                BlogStatus.DRAFT
        );
    }

    public static Blog of(
            String blogId,
            String authorId,
            String title,
            String slug,
            Set<Tag> tags,
            String blogContent,
            BlogStatus status,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt
            ) {
        return new Blog(
                BlogId.of(blogId),
                authorId,
                tags,
                BlogTitle.of(title),
                Slug.of(slug),
                status,
                BlogContent.of(blogContent),
                createdAt,
                updatedAt
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

    public BlogId getBlogId() {
        return blogId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public BlogTitle getTitle() {
        return title;
    }

    public Slug getSlug() {
        return slug;
    }

    public BlogContent getContent() {
        return content;
    }

    public BlogStatus getStatus() {
        return status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
