public class MyArraySizeException extends Exception {
    @Override
    public String getMessage() {
        return "Массив больше, чем 4х4";
    }
}
