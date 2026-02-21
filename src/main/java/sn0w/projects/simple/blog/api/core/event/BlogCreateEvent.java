package sn0w.projects.simple.blog.api.core.event;

import sn0w.projects.simple.blog.api.core.domain.author.AuthorId;
import sn0w.projects.simple.blog.api.core.domain.blog.*;
import sn0w.projects.simple.blog.api.core.domain.tag.Tag;

import java.time.OffsetDateTime;
import java.util.Set;

public class BlogCreateEvent implements DomainEvent {
    private final BlogId blogId;
    private final AuthorId authorId;
    private final BlogTitle title;
    private final Slug slug;
    private final BlogContent content;
    private final Set<Tag> tags;
    private final BlogStatus status;
    private final OffsetDateTime occurredAt;

    public BlogCreateEvent(
            BlogId blogId,
            AuthorId authorId,
            BlogTitle title,
            Slug slug,
            BlogContent content,
            Set<Tag> tags,
            BlogStatus status,
            OffsetDateTime occurredAt) {
        this.blogId = blogId;
        this.authorId = authorId;
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.tags = tags;
        this.status = status;
        this.occurredAt = occurredAt;
    }

    @Override
    public String getEventType() {
        return "";
    }

    @Override
    public OffsetDateTime occurredAt() {
        return null;
    }

    public BlogId getBlogId() {
        return blogId;
    }

    public AuthorId getAuthorId() {
        return authorId;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public BlogStatus getStatus() {
        return status;
    }

    public OffsetDateTime getOccurredAt() {
        return occurredAt;
    }
}
