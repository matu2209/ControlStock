package Productos;


import Interfaces.Buscable;
import Interfaces.Filtrable;
import enumeradores.Empresa;
import enumeradores.Prioridad;

import java.util.Objects;

public abstract class Producto implements Comparable<Producto>, Buscable <Integer>, Filtrable<Prioridad> {
    private Integer hashProducto;
    private String marca;
    private String articulo;
    private Integer talle;
    private Integer stock; //STOCK TOTAL en el CENTRO DE DISTRIBUCION
    private Double volumen;
    private Prioridad prioridad;
    private Empresa empresa;

    public Producto(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Empresa empresa) {
        this.hashProducto = Objects.hash(marca, articulo, talle);
        this.marca = marca;
        this.articulo = articulo;
        this.talle = talle;
        this.stock = stock;
        this.volumen = volumen;
        this.prioridad = prioridad;
        this.empresa = empresa; 
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(getHashProducto(), producto.getHashProducto()) && Objects.equals(getMarca(), producto.getMarca()) && Objects.equals(getArticulo(), producto.getArticulo()) && Objects.equals(getTalle(), producto.getTalle()) && Objects.equals(getStock(), producto.getStock()) && Objects.equals(getVolumen(), producto.getVolumen()) && getPrioridad() == producto.getPrioridad() && getEmpresa() == producto.getEmpresa();
    }

    @Override
    public int hashCode() {
        return this.getHashProducto();
    }

    @Override
    public boolean buscar(Integer hashProductoBuscado) {       //Busca por el hash del producto, devuelve el objeto si lo encuentra o null si no lo logra
        return this.hashProducto.equals(hashProductoBuscado);
    }

    @Override
    public int compareTo(Producto o) {
        return this.getHashProducto().compareTo(o.getHashProducto());
    }

    @Override
    public boolean filter(Prioridad parametroFiltrado) {
        return this.getPrioridad().equals(parametroFiltrado);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "hashProducto=" + hashProducto +
                ", marca='" + marca + '\'' +
                ", articulo='" + articulo + '\'' +
                ", talle=" + talle +
                ", stock=" + stock +
                ", volumen=" + volumen +
                ", prioridad=" + prioridad +
                ", empresa=" + empresa +
                '}';
    }
}


