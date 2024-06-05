package Productos;


import Interfaces.Buscable;
import enumeradores.Prioridad;

import java.util.Objects;

public abstract class Producto implements Buscable <Producto, Integer> {
    private Integer hashProducto;
    private String marca;
    private String articulo;
    private Integer talle;
    private Integer stock; //STOCK TOTAL DEL CENTRO DE DISTRIBUCION
    private Double volumen;//armar una sobreescritura directa de volumen se me ocurre
    private Prioridad prioridad;

    public Producto(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad) {
        this.hashProducto = Objects.hash(marca, articulo, talle);
        this.marca = marca;
        this.articulo = articulo;
        this.talle = talle;
        this.stock = stock;
        this.volumen = volumen;
        this.prioridad = prioridad;
    }

    public Integer getHashProducto() {
        return hashProducto;
    }

    public void setHashProducto(Integer hashProducto) {
        this.hashProducto = hashProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Integer getTalle() {
        return talle;
    }

    public void setTalle(Integer talle) {
        this.talle = talle;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(getHashProducto(), producto.getHashProducto()) && Objects.equals(getMarca(), producto.getMarca()) && Objects.equals(getArticulo(), producto.getArticulo()) && Objects.equals(getTalle(), producto.getTalle()) && Objects.equals(getStock(), producto.getStock()) && Objects.equals(getVolumen(), producto.getVolumen()) && getPrioridad() == producto.getPrioridad();
    }

    @Override
    public int hashCode() {
        return this.getHashProducto();
    }

    @Override
    public Producto buscar(Integer hashProductoBuscado) {       //Busca por el hash del producto, devuelve el objeto si lo encuentra o null si no lo logra

        if (this.hashProducto.equals(hashProductoBuscado)) {
            return this;
        }
        return null;

    }

    //public abstract int calcularPrioridad(Producto producto);//metodo capaz para calcular prioridad se me ocurre o prefieren enum?
    public void agregarProducto(Producto producto) {};//despues armo logica
    public void eliminarProducto(Producto producto) {};
    public void modificarProducto(Producto producto) {};

}


