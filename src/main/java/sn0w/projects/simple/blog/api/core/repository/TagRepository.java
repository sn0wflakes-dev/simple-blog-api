package sn0w.projects.simple.blog.api.core.repository;

import sn0w.projects.simple.blog.api.core.domain.tag.Tag;

import java.util.Optional;

public interface TagRepository {
    Tag findTagById(String tagId);
    Optional<Tag> findTagByName(String tagName);
    void save(Tag tag);
}
