package sn0w.projects.simple.blog.api.presentation.rest.model.blog;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record AddBlogReq(
        @NotBlank
        String title,

        @NotBlank
        String authorId,

        @NotBlank
        Set<String> tagNames,

        @NotBlank
        String content
) {
}
