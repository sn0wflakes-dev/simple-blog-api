package sn0w.projects.simple.blog.api.infrastructure.jdbc.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sn0w.projects.simple.blog.api.core.domain.tag.Tag;
import sn0w.projects.simple.blog.api.core.repository.TagRepository;
import sn0w.projects.simple.blog.api.infrastructure.jdbc.mapper.TagRowMapper;

import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final TagRowMapper tagRowMapper;

    public TagRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            TagRowMapper tagRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.tagRowMapper = tagRowMapper;
    }

    @Override
    public Tag findTagById(String tagId) {
        String sql = """
                SELECT id, tag
                FROM tags
                WHERE id = :id
                """;

        MapSqlParameterSource sqlParam = new MapSqlParameterSource()
                .addValue("id", tagId);

        return namedParameterJdbcTemplate.queryForObject(sql, sqlParam, tagRowMapper);
    }

    @Override
    public Optional<Tag> findTagByName(String tagName) {
        String sql = """
                SELECT id, tag
                FROM tags
                WHERE tag = :tag
                """;

        MapSqlParameterSource sqlParam = new MapSqlParameterSource()
                .addValue("tag", tagName);

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, sqlParam, tagRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Tag tag) {
        String sql = """
                INSERT INTO tags (id, tag)
                VALUES (:id, :tag)
                ON CONFLICT (id) DO NOTHING
                """;

        MapSqlParameterSource sqlParam = new MapSqlParameterSource()
                .addValue("id", tag.getTagId().getValue())
                .addValue("tag", tag.getTagName().getValue());

        namedParameterJdbcTemplate.update(sql, sqlParam);
    }
}
