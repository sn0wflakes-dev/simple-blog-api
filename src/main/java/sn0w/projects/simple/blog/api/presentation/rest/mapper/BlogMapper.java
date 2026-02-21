package sn0w.projects.simple.blog.api.presentation.rest.mapper;

import sn0w.projects.simple.blog.api.core.domain.blog.Blog;
import sn0w.projects.simple.blog.api.core.domain.tag.Tag;
import sn0w.projects.simple.blog.api.presentation.rest.model.blog.AddBlogReq;

import java.util.stream.Collectors;

public class BlogMapper {
    public static Blog addReqToDomain(AddBlogReq dto) {
        return Blog.create(
                dto.authorId(),
                dto.tagNames().stream().map(Tag::create).collect(Collectors.toSet()),
                dto.title(),
                dto.content()
        );
    }
}
