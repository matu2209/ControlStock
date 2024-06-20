package Productos;

import enumeradores.Empresa;
import enumeradores.Prioridad;
import enumeradores.Segmento;

import java.util.Objects;

public class Calzado extends Producto {
    private final Segmento segmento;

    public Calzado(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Segmento segmento, Empresa empresa) {
        super(marca, articulo, talle, stock, volumen, prioridad, empresa);
        this.segmento = segmento;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Calzado calzado = (Calzado) o;
        return getSegmento() == calzado.getSegmento();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSegmento());
    }

    @Override
    public String toString() {
        return "Calzado{" +
                "segmento=" + segmento +
                '}';
    }
}
