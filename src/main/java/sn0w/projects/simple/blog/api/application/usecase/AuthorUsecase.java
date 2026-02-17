package sn0w.projects.simple.blog.api.application.usecase;

import sn0w.projects.simple.blog.api.core.domain.author.Author;

public interface AuthorUsecase {
    void addAuthorRes(Author data);
    Author getAuthorById(String authorId);
}
