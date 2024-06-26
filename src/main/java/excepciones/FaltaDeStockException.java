package excepciones;

public class FaltaDeStockException extends Exception {
    private final Integer hashProducto;

    public FaltaDeStockException(Integer hashProducto) {
        this.hashProducto = hashProducto;
    }

    public String getMessage(String mesagge) {
        return String.format("El producto ",this.hashProducto," no tiene stock suficiente");
    }
}