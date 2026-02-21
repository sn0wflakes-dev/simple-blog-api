package sn0w.projects.simple.blog.api.core.event;

import sn0w.projects.simple.blog.api.core.domain.blog.BlogId;

import java.time.OffsetDateTime;

public class BlogUpdateEvent implements DomainEvent {
    private final BlogId blogId;
    private final String content;
    private final Integer contentVersion;
    private final OffsetDateTime occurredAt;

    public BlogUpdateEvent(BlogId blogId, String content, Integer contentVersion, OffsetDateTime occurredAt) {
        this.blogId = blogId;
        this.content = content;
        this.contentVersion = contentVersion;
        this.occurredAt = occurredAt;
    }

    public BlogId getBlogId() {
        return blogId;
    }

    public String getContent() {
        return content;
    }

    public Integer getContentVersion() {
        return contentVersion;
    }

    public OffsetDateTime getOccurredAt() {
        return occurredAt;
    }

    @Override
    public String getEventType() {
        return "";
    }

    @Override
    public OffsetDateTime occurredAt() {
        return null;
    }
}
