package sn0w.projects.simple.blog.api.presentation.rest.model.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBlogRes {
    private String message;
    private String blogId;
}
