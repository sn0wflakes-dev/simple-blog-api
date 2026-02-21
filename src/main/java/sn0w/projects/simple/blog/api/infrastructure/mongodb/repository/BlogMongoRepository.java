package sn0w.projects.simple.blog.api.infrastructure.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sn0w.projects.simple.blog.api.core.domain.blog.Blog;
import sn0w.projects.simple.blog.api.infrastructure.mongodb.entity.BlogDocuments;

import java.util.List;

@Repository
public interface BlogMongoRepository extends MongoRepository<BlogDocuments, String> {
    List<BlogDocuments> getBlogDocumentsById(String id);
}
