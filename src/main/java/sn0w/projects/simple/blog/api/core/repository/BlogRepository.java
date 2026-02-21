package sn0w.projects.simple.blog.api.core.repository;

import sn0w.projects.simple.blog.api.core.domain.blog.Blog;

public interface BlogRepository {
    void addBlog(Blog data);
    Blog getBlogById(String id);
    void delete(String id);
}
