package sn0w.projects.simple.blog.api.presentation.rest.model.author;

import jakarta.validation.constraints.NotBlank;

public record AddAuthorReq(
        @NotBlank
        String authorName
) {
        @Override
        public String authorName() {
                return authorName;
        }
}
