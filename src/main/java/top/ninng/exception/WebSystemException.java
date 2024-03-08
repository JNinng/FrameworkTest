package top.ninng.exception;

/**
 * Web 测试异常
 */
public class WebSystemException extends Exception {

    private String message;

    public WebSystemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
