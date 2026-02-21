package sn0w.projects.simple.blog.api.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @Column(name = "id", length = 37)
    private String authorId;

    @Column(name = "name", length = 100)
    private String authorName;
}
