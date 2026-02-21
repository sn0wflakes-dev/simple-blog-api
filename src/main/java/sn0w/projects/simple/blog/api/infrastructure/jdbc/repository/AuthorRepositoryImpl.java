package sn0w.projects.simple.blog.api.infrastructure.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sn0w.projects.simple.blog.api.core.repository.AuthorRepository;
import sn0w.projects.simple.blog.api.core.domain.author.Author;
import sn0w.projects.simple.blog.api.infrastructure.jdbc.mapper.AuthorRowMapper;

@Slf4j
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final AuthorRowMapper authorRowMapper;

    public AuthorRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            AuthorRowMapper authorRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.authorRowMapper = authorRowMapper;
    }

    @Override
    public void addAuthor(Author author) {
        String sqlQuery = "INSERT INTO authors (id, name) VALUES (:authorId, :authorName)";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("authorId", author.getAuthorId().getValue());
        sqlParam.addValue("authorName", author.getAuthorName().getValue());

        namedParameterJdbcTemplate.update(sqlQuery, sqlParam);
    }

    @Override
    public Author getAuthorById(String authorId) {
        log.info("authorId : {}", authorId);
        String sqlQuery = "SELECT id, name FROM authors WHERE id = :id";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("id", authorId);

        return namedParameterJdbcTemplate.queryForObject(sqlQuery, sqlParam, authorRowMapper);
    }
}
