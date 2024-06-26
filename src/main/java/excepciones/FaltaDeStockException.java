package excepciones;

public class FaltaDeStockException extends Exception {
    public FaltaDeStockException(String mensaje) {
        super(mensaje);
    }
}
