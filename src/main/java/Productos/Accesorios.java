package Productos;

import enumeradores.Disciplina;
import enumeradores.Prioridad;

public class Accesorios extends Producto{

    private Disciplina disciplina;

    public Accesorios(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Disciplina disciplina) {
        super(marca,articulo,talle,stock,volumen,prioridad);
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

}
