package estanteria;

import java.util.ArrayList;
import java.util.List;

/**Esta clase almacena los diferentes tipos de productos
 -Tener en cuenta que al agregar los pructos se descuenta el volumen total
 y a medida que salen productos se vuelve agregar volumen
 -Una vez completo el 95% de ocupacion se bloqueara para no tener que entrar
 a guardar productos**/
public class Modulo <T> {

    private int volumen;
    private boolean completo;
    private List<T> productos;

    //constuctor
    public Modulo() {
        this.volumen = 100;
        this.completo = false;
        this.productos = new ArrayList<>();
        //crear la lista generica
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public void agregarObjeto(T producto) {
        this.productos.add(producto);
    }

    public List<T> getProductos() {
        return productos;
    }
    public void mostrarContenido() {
        for (T producto : productos) {
            System.out.println(producto);
        }
    }

}
