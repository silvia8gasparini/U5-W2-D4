package it.epicode.U5W2D4practice.exception;

public class BlogPostNotFoundException extends RuntimeException {
    public BlogPostNotFoundException(String message) {
        super(message);
    }
}
