package excepciones;

public class FaltaDeEspacioException extends Exception{
    private final String message;

    public FaltaDeEspacioException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
 