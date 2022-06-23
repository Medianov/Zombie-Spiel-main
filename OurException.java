public class OurException extends Exception {

    @Override
    public String getMessage() {
        return "Die Figuren überlapen sich !";
    }
}
