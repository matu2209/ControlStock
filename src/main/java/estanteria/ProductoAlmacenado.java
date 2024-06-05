package estanteria;

import java.util.Objects;

public class ProductoAlmacenado {
    private Integer hashProducto;
    private Integer stockDePosicion;//STOCK QUE ALMACENE EN UNA POSICION
    private Float VolumenProducto;
    private Float sumatoriaVolumen;

    public ProductoAlmacenado(Integer hashProducto, Integer stockDePosicion, Float VolumenProducto) {
        this.hashProducto = hashProducto;
        this.stockDePosicion = stockDePosicion;
        this.VolumenProducto = VolumenProducto;
        this.sumatoriaVolumen = VolumenProducto*stockDePosicion;
    }

    public Integer getHashProducto() {
        return hashProducto;
    }

    public void setHashProducto(Integer hashProducto) {
        this.hashProducto = hashProducto;
    }

    public Integer getStockDePosicion() {
        return stockDePosicion;
    }

    public void setStockDePosicion(Integer stockDePosicion) {
        this.stockDePosicion = stockDePosicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoAlmacenado that = (ProductoAlmacenado) o;
        return Objects.equals(getHashProducto(), that.getHashProducto()) && Objects.equals(getStockDePosicion(), that.getStockDePosicion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHashProducto(), getStockDePosicion());
    }

    @Override
    public String toString() {
        return "ProductoAlmacenado{" +
                "hashProducto=" + hashProducto +
                ", stockDePosicion=" + stockDePosicion +
                '}';
    }
}
