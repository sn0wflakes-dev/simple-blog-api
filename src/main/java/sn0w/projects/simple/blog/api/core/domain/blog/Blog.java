package sn0w.projects.simple.blog.api.core.domain.Blog;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

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
}
