package sn0w.projects.simple.blog.api.infrastructure.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn0w.projects.simple.blog.api.core.domain.blog.Blog;
import sn0w.projects.simple.blog.api.core.domain.blog.BlogId;
import sn0w.projects.simple.blog.api.core.domain.tag.Tag;
import sn0w.projects.simple.blog.api.core.repository.BlogRepository;
import sn0w.projects.simple.blog.api.core.repository.TagRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@Slf4j
public class BlogRepositoryImpl implements BlogRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final TagRepository tagRepository;

    public BlogRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            TagRepository tagRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void addBlog(Blog data) {
        // Add metadata first
        addMetaDataBlog(data);

        // Add or register tags
        Set<Tag> tagsIds = processTags(data.getTags());

        // Insert tag and id
        insertTagsToBlog(data.getBlogId(), tagsIds);
    }

    @Override
    public Blog getBlogById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    private void addMetaDataBlog(Blog data) {
        String sqlQuery = "INSERT INTO blogs (id, author_id, blog_title, slug) " +
                "VALUES (:id, :authorId, :title, :slug)";

        MapSqlParameterSource sqlParam = new MapSqlParameterSource()
                .addValue("id", data.getBlogId().blogId())
                .addValue("authorId", data.getAuthorId())
                .addValue("title", data.getTitle().getValue())
                .addValue("slug", data.getSlug().getValue());

        namedParameterJdbcTemplate.update(sqlQuery, sqlParam);
    }

    private Set<Tag> processTags(Set<Tag> tagNames) {
        Set<Tag> tagIds = new HashSet<>();

        for (Tag tagName: tagNames) {
            Tag tagId = findOrCreateTag(tagName);
            tagIds.add(tagId);
        }

        return tagIds;
    }

    private Tag findOrCreateTag(Tag tag) {
        Optional<Tag> tagData = tagRepository.findTagByName(tag.getTagName().getValue());

        if (tagData.isPresent()) {
            log.info("Existing Tag found with id : {} and name {}",
                    tagData.get().getTagId().getValue(),
                    tagData.get().getTagName().getValue());
            return tagData.get();
        }

        tagRepository.save(tag);

        return tag;
    }

    private void insertTagsToBlog(BlogId blogId, Set<Tag> tags) {
        String sql = """
                INSERT INTO blog_tags (blog_id, tag_id)
                VALUES (:blogId, :tagId)
                """;

        SqlParameterSource[] source = tags.stream()
                .map(tagId -> new MapSqlParameterSource()
                        .addValue("blogId", blogId.blogId())
                        .addValue("tagId", tagId.getTagId().getValue())
                ).toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, source);
    }

}
