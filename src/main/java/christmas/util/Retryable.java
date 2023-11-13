package christmas.util;

@FunctionalInterface
public interface Retryable<T> {
    public T retry() throws IllegalArgumentException;
}