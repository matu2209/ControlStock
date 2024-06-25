package Productos;

import enumeradores.Disciplina;
import enumeradores.Empresa;
import enumeradores.Prioridad;
import enumeradores.Segmento;

import java.util.Objects;

public class Accesorios extends Producto{

    private final Disciplina disciplina;

    public Accesorios(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Disciplina disciplina, Empresa empresa) {
        super(marca,articulo,talle,stock,volumen,prioridad, empresa);
        this.disciplina = disciplina;
    }

    public Accesorios(Integer hashProducto, String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Disciplina disciplina, Empresa empresa) {
        super(hashProducto,marca,articulo,talle,stock,volumen,prioridad, empresa);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Accesorios that = (Accesorios) o;
        return getDisciplina() == that.getDisciplina();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDisciplina());
    }

    @Override
    public String toString() {
        return super.toString()+
                ", disciplina:" + disciplina+'}';
    }
}
