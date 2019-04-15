package exception.example;

public class ExceptionExample extends Exception{
    private String error;

    public ExceptionExample(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
