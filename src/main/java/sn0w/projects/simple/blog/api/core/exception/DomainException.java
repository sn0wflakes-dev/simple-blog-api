package sn0w.projects.simple.blog.api.core.exception;

public abstract class DomainException extends RuntimeException {
    private final String errorType;

    protected DomainException(String message, String errorType) {
        super(message);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }
}
