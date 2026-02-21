package sn0w.projects.simple.blog.api.presentation.rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn0w.projects.simple.blog.api.application.usecase.AuthorUsecase;
import sn0w.projects.simple.blog.api.core.domain.author.Author;
import sn0w.projects.simple.blog.api.presentation.rest.mapper.AuthorMapper;
import sn0w.projects.simple.blog.api.presentation.rest.model.WebRes;
import sn0w.projects.simple.blog.api.presentation.rest.model.author.AddAuthorReq;
import sn0w.projects.simple.blog.api.presentation.rest.model.author.AddAuthorRes;
import sn0w.projects.simple.blog.api.presentation.rest.model.author.GetAuthorRes;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("api/author")
public class AuthorController {

    @Autowired
    private final AuthorUsecase service;

    public AuthorController(AuthorUsecase service) {
        this.service = service;
    }

    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebRes<AddAuthorRes>> addAuthorController(
            @RequestBody AddAuthorReq request,
            HttpServletRequest servletRequest) {

        Author data = AuthorMapper.addReqToDomain(request);
        service.addAuthorRes(data);

        WebRes<AddAuthorRes> apiResponse = WebRes.<AddAuthorRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(Instant.now().toString())
                        .build())
                .data(AddAuthorRes.builder()
                        .message("Success add author")
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
    public ResponseEntity<WebRes<GetAuthorRes>> getAuthorByIdController(
            @PathVariable String id,
            HttpServletRequest servletRequest
    ) {
        Author data = service.getAuthorById(id);
        GetAuthorRes response = AuthorMapper.domainToGetRes(data);

        WebRes<GetAuthorRes> apiResponse = WebRes.<GetAuthorRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(Instant.now().toString())
                        .build())
                .data(GetAuthorRes.builder()
                        .authorId(response.getAuthorId())
                        .authorName(response.getAuthorName())
                        .build())
                .errors(null)
                .path(servletRequest.getRequestURI())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
