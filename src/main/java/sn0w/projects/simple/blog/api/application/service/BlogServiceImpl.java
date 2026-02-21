package sn0w.projects.simple.blog.api.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn0w.projects.simple.blog.api.application.config.EventPublisher;
import sn0w.projects.simple.blog.api.application.mapper.BlogMapper;
import sn0w.projects.simple.blog.api.application.usecase.BlogUsecase;
import sn0w.projects.simple.blog.api.core.domain.author.Author;
import sn0w.projects.simple.blog.api.core.domain.blog.Blog;
import sn0w.projects.simple.blog.api.core.event.BlogCreateEvent;
import sn0w.projects.simple.blog.api.core.repository.AuthorRepository;
import sn0w.projects.simple.blog.api.core.repository.BlogRepository;
import sn0w.projects.simple.blog.api.infrastructure.mongodb.entity.BlogDocuments;
import sn0w.projects.simple.blog.api.infrastructure.mongodb.repository.BlogMongoRepository;

import java.time.OffsetDateTime;

@Service
@Slf4j
public class BlogServiceImpl implements BlogUsecase {

    @Autowired
    private final BlogRepository blogRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final EventPublisher eventPublisher;

    @Autowired
    private final BlogMongoRepository blogMongoRepository;

    public BlogServiceImpl(
            BlogRepository blogRepository,
            AuthorRepository authorRepository,
            EventPublisher eventPublisher, BlogMongoRepository blogMongoRepository) {
        this.blogRepository = blogRepository;
        this.authorRepository = authorRepository;
        this.eventPublisher = eventPublisher;
        this.blogMongoRepository = blogMongoRepository;
    }

    @Override
    public void createBlog(Blog blog) {
        Author authorData = authorRepository.getAuthorById(blog.getAuthorId());

        if (authorData.getAuthorId().getValue().isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        blogRepository.addBlog(blog);

        eventPublisher.publish(new BlogCreateEvent(
                blog.getBlogId(),
                authorData.getAuthorId(),
                blog.getTitle(),
                blog.getSlug(),
                blog.getContent(),
                blog.getTags(),
                blog.getStatus(),
                OffsetDateTime.now()
        ));

        log.info("Blog added with content : {}", blog.getContent().getValue());


    }

    @Override
    public Blog getBlog(String blogId) {
        BlogDocuments data = blogMongoRepository.findById(blogId).orElseThrow(
                () -> new RuntimeException("Data not found")
        );
        return BlogMapper.toDomain(data);
    }
}
