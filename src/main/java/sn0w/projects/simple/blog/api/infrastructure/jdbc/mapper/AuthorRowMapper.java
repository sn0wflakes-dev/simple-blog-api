package sn0w.projects.simple.blog.api.infrastructure.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import sn0w.projects.simple.blog.api.core.domain.Author.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Author.create(
                rs.getString("name")
        );
    }
}
