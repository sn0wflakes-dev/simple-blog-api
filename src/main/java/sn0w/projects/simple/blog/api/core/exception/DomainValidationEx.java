package sn0w.projects.simple.blog.api.core.exception;

import java.util.ArrayList;
import java.util.List;

public class DomainValidationEx extends DomainException{

    private final List<ValidationError> validationErrors;

    protected DomainValidationEx(List<ValidationError> validationErrors) {
        super("Domain validation failed", "VALIDATION_ERROR");
        this.validationErrors = new ArrayList<>(validationErrors);
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public static class ValidationError{
        private final String field;
        private final String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getField() {
            return field;
        }
    }
}
