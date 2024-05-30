package Productos;


public abstract class Producto {
    private String id;
    private String marca;
    private String modelo;
    private int volumen;//armar una sobreescritura directa de volumen se me ocurre
    private int talle;

    public Producto(String id, String marca, String modelo, int volumen, int talle) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.volumen = volumen;
        this.talle = talle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getTalle() {
        return talle;
    }

    public void setTalle(int talle) {
        this.talle = talle;
    }

    public abstract int calcularPrioridad(Producto producto);//metodo capaz para calcular prioridad se me ocurre o prefieren enum?
    public void agregarProducto(Producto producto) {};//despues armo logica
    public void eliminarProducto(Producto producto) {};
    public void modificarProducto(Producto producto) {};

}

