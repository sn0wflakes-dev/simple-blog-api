package sn0w.projects.simple.blog.api.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn0w.projects.simple.blog.api.infrastructure.jpa.entity.BlogEntity;

@Repository
public interface BlogJpaRepository extends JpaRepository<BlogEntity, String> {
}
