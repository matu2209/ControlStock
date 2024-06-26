package excepciones;

public class PosicionNoEncontradaException extends Exception {
    public PosicionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}