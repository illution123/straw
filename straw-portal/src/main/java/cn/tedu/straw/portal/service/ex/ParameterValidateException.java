package cn.tedu.straw.portal.service.ex;

public class ParameterValidateException extends RuntimeException {
    public ParameterValidateException() {
    }

    public ParameterValidateException(String message) {
        super(message);
    }

    public ParameterValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterValidateException(Throwable cause) {
        super(cause);
    }

    public ParameterValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
