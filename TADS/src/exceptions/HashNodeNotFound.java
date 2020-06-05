package exceptions;

public class HashNodeNotFound extends RuntimeException {

    public HashNodeNotFound(){
        super("Element not in table");
    }
}
