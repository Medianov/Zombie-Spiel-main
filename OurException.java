public class OurException extends Exception {

    @Override
    public String getMessage() {
        return "Fehlerhafte Eingabe!";
    }
}
