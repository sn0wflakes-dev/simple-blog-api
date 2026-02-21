package sn0w.projects.simple.blog.api.infrastructure.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Set;

@Document(collection = "blog_content")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDocuments {
    @Id
    private String id;

    private AuthorInfo author;

    private String title;
    private String slug;

    private String status;

    private String content;
    private Integer contentVersion;

    private Set<String> tags;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private Instant publishedAt;

    private Instant lastSyncedAt;
    private Integer syncVersion;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorInfo {
        private String authorId;
        private String authorName;
    }
}
