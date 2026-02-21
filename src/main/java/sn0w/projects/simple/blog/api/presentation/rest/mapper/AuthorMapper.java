package sn0w.projects.simple.blog.api.presentation.rest.mapper;

import sn0w.projects.simple.blog.api.core.domain.author.Author;
import sn0w.projects.simple.blog.api.presentation.rest.model.author.AddAuthorReq;
import sn0w.projects.simple.blog.api.presentation.rest.model.author.GetAuthorRes;

public class AuthorMapper {
    public static Author addReqToDomain(AddAuthorReq dto) {
        return Author.create(dto.authorName());
    }

    public static GetAuthorRes domainToGetRes(Author data) {
        return new GetAuthorRes(
                data.getAuthorId().getValue(),
                data.getAuthorName().getValue()
        );
    }
}
