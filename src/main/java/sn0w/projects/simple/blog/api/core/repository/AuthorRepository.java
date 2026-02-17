package sn0w.projects.simple.blog.api.core.repository;

import sn0w.projects.simple.blog.api.core.domain.Author.Author;

public interface AuthorRepository {
    void addAuthor(Author author);
    Author getAuthorById(String authorId);
}
