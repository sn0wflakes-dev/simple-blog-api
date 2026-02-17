package sn0w.projects.simple.blog.api.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn0w.projects.simple.blog.api.application.usecase.AuthorUsecase;
import sn0w.projects.simple.blog.api.core.domain.author.Author;
import sn0w.projects.simple.blog.api.core.repository.AuthorRepository;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorUsecase {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAuthorRes(Author data) {
        repository.addAuthor(data);
    }

    @Override
    public Author getAuthorById(String authorId) {
        return repository.getAuthorById(authorId);
    }
}
