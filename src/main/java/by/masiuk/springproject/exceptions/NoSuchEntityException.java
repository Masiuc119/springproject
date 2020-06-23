package by.masiuk.springproject.exceptions;

public class NoSuchEntityException extends Exception {
    public NoSuchEntityException(String message) {
        super(message);
    }
}