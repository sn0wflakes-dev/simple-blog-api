package sn0w.projects.simple.blog.api.presentation.rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn0w.projects.simple.blog.api.application.usecase.BlogUsecase;
import sn0w.projects.simple.blog.api.core.domain.blog.Blog;
import sn0w.projects.simple.blog.api.presentation.rest.mapper.BlogMapper;
import sn0w.projects.simple.blog.api.presentation.rest.model.WebRes;
import sn0w.projects.simple.blog.api.presentation.rest.model.blog.AddBlogReq;
import sn0w.projects.simple.blog.api.presentation.rest.model.blog.AddBlogRes;
import sn0w.projects.simple.blog.api.presentation.rest.model.blog.GetBlogRes;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/blog")
public class BlogController {

    @Autowired
    private final BlogUsecase service;

    public BlogController(BlogUsecase service) {
        this.service = service;
    }

    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebRes<AddBlogRes>> addBlogController(
            @RequestBody AddBlogReq request,
            HttpServletRequest servletRequest) {

        Blog data = BlogMapper.addReqToDomain(request);
        service.createBlog(data);

        WebRes<AddBlogRes> apiResponse = WebRes.<AddBlogRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(Instant.now().toString())
                        .build())
                .data(AddBlogRes.builder()
                        .message("Success add blog")
                        .blogId(request.authorId())
                        .build())
                .errors(null)
                .path(servletRequest.getRequestURI())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebRes<GetBlogRes>> getBlogController(
            @PathVariable String id,
            HttpServletRequest servletRequest) {

        Blog blog = service.getBlog(id);

        WebRes<GetBlogRes> apiResponse = WebRes.<GetBlogRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(Instant.now().toString())
                        .build())
                .data(GetBlogRes.builder()
                        .authorName("Sn0w")
                        .title(blog.getTitle().getValue())
                        .slug(blog.getSlug().getValue())
                        .tags(blog.getTags().stream().map(tag -> tag.getTagName().getValue()).collect(Collectors.toSet()))
                        .content(blog.getContent().getValue())
                        .updatedAt(blog.getUpdatedAt())
                        .build())
                .errors(null)
                .path(servletRequest.getRequestURI())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
