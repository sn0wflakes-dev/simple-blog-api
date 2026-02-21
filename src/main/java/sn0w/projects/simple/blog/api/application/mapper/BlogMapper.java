package sn0w.projects.simple.blog.api.application.mapper;

import lombok.extern.slf4j.Slf4j;
import sn0w.projects.simple.blog.api.application.utils.TimeConverter;
import sn0w.projects.simple.blog.api.core.domain.blog.Blog;
import sn0w.projects.simple.blog.api.core.domain.blog.BlogStatus;
import sn0w.projects.simple.blog.api.core.domain.tag.Tag;
import sn0w.projects.simple.blog.api.infrastructure.mongodb.entity.BlogDocuments;

import java.util.stream.Collectors;

@Slf4j
public class BlogMapper {
    public static Blog toDomain(BlogDocuments doc) {
        log.info("Slug : {}", doc.getSlug());
        return Blog.of(
                doc.getId(),
                doc.getAuthor().getAuthorName(),
                doc.getTitle(),
                doc.getSlug(),
                doc.getTags().stream().map(tag -> Tag.of("1", tag)).collect(Collectors.toSet()),
                doc.getContent(),
                BlogStatus.valueOf(doc.getStatus()),
                TimeConverter.offsiteConverter(doc.getCreatedAt()),
                TimeConverter.offsiteConverter(doc.getUpdatedAt())
        );
    }
}
