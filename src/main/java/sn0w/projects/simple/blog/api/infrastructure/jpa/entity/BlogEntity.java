package sn0w.projects.simple.blog.api.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import sn0w.projects.simple.blog.api.core.domain.blog.BlogStatus;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "blogs")
public class BlogEntity {
    @Id
    @Column(name = "id", length = 37)
    private String blogId;

    @Column(name = "blog_title", length = 100)
    private String blogTitle;

    @Column(name = "author_id", length = 37)
    private String authorId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BlogStatus status;

    @Column(name = "slug", length = 200)
    private String slug;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
