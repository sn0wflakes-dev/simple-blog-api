package sn0w.projects.simple.blog.api.application.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn0w.projects.simple.blog.api.core.event.BlogCreateEvent;
import sn0w.projects.simple.blog.api.core.repository.AuthorRepository;
import sn0w.projects.simple.blog.api.core.repository.BlogRepository;
import sn0w.projects.simple.blog.api.infrastructure.jpa.entity.AuthorEntity;
import sn0w.projects.simple.blog.api.infrastructure.jpa.entity.BlogEntity;
import sn0w.projects.simple.blog.api.infrastructure.jpa.repository.AuthorJpaRepository;
import sn0w.projects.simple.blog.api.infrastructure.jpa.repository.BlogJpaRepository;
import sn0w.projects.simple.blog.api.infrastructure.mongodb.entity.BlogDocuments;
import sn0w.projects.simple.blog.api.infrastructure.mongodb.repository.BlogMongoRepository;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BlogEventSyncService {

    private final BlogRepository blogRepository;
    private final BlogJpaRepository blogJpaRepository;
    private final AuthorRepository authorRepository;
    private final AuthorJpaRepository authorJpaRepository;
    private final BlogMongoRepository blogMongoRepository;

    public BlogEventSyncService(
            BlogRepository blogRepository,
            BlogJpaRepository blogJpaRepository,
            AuthorRepository authorRepository, AuthorJpaRepository authorJpaRepository,
            BlogMongoRepository blogMongoRepository) {
        this.blogRepository = blogRepository;
        this.blogJpaRepository = blogJpaRepository;
        this.authorRepository = authorRepository;
        this.authorJpaRepository = authorJpaRepository;
        this.blogMongoRepository = blogMongoRepository;
    }

    public void syncBlogCreation(BlogCreateEvent event) {
        BlogEntity blogData = blogJpaRepository.findById(event.getBlogId().blogId()).orElseThrow(
                () -> new RuntimeException("Blog not found")
        );

        log.info("Author name from blog event : {}", event.getAuthorId().getValue());

        AuthorEntity authorData = authorJpaRepository.findById(event.getAuthorId().getValue()).orElseThrow(
                () -> new RuntimeException("Author not found")
        );

        BlogDocuments blogDocuments = BlogDocuments.builder()
                .id(event.getBlogId().blogId())
                .author(BlogDocuments.AuthorInfo.builder()
                        .authorId(authorData.getAuthorId())
                        .authorName(authorData.getAuthorName())
                        .build())
                .title(event.getTitle().getValue())
                .slug(event.getSlug().getValue())
                .status(blogData.getStatus().name())
                .content(event.getContent().getValue())
                .contentVersion(1)
                .tags(event.getTags().stream().map(tag -> tag.getTagName().getValue()).collect(Collectors.toSet()))
                .createdAt(blogData.getCreatedAt().toInstant())
                .updatedAt(blogData.getUpdatedAt().toInstant())
                .lastSyncedAt(Instant.now())
                .syncVersion(1)
                .build();

        blogMongoRepository.save(blogDocuments);

        log.info("Success to add blog with id : {}", event.getBlogId().blogId());
    }
}
