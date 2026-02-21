package sn0w.projects.simple.blog.api.presentation.rest.model.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBlogRes {
    private String authorName;
    private String title;
    private String slug;
    private Set<String> tags;
    private String content;
    private OffsetDateTime updatedAt;
}
