public class FestaException extends Exception {
    private String error = "";

    FestaException(String error) {
        this.error = error;
    }

    String getError() {
        return error;
    }
}
