package christmas.util;

public class InputErrorHandler<T> {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String ERROR_MESSAGE_SUFFIX = " 다시 입력해 주세요.";


    private final String errorMessage;

    public InputErrorHandler(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T printErrorAndRetry(Retryable<T> retryable) {
        T returnByRetry = null;
        while (returnByRetry == null) {
            try {
                returnByRetry = retryable.retry();
            } catch (IllegalArgumentException e) {
                printErrorMessage();
            }
        }
        return returnByRetry;
    }

    private void printErrorMessage() {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage + ERROR_MESSAGE_SUFFIX);
    }
}
