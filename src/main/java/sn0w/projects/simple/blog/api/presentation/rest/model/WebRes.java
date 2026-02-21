package sn0w.projects.simple.blog.api.presentation.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"meta", "data", "errors", "path"})
public class WebRes<T> {
    private Meta meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Errors errors;

    private String path;

    @Data
    @Builder
    @JsonPropertyOrder({"requestId", "timestamp"})
    public static class Meta {
        private String requestId;
        private String timestamp;
    }

    @Data
    @Builder
    @JsonPropertyOrder({"errorType", "message", "details"})
    public static class Errors{
        private String errorType;
        private String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object details;
    }
}
