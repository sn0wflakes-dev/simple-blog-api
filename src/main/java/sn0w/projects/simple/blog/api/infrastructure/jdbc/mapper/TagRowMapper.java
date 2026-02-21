package sn0w.projects.simple.blog.api.infrastructure.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import sn0w.projects.simple.blog.api.core.domain.tag.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TagRowMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tag.of(
                rs.getString("id"),
                rs.getString("tag")
        );
    }
}
