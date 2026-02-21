package sn0w.projects.simple.blog.api.application.usecase;

import sn0w.projects.simple.blog.api.core.domain.blog.Blog;

public interface BlogUsecase {
    void createBlog(Blog blog);
    Blog getBlog(String blogId);
}
