import estanteria.Estanteria;
import enumeradores.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEstanteria {
    @Test
    public void agregarProductoEstanteria_RevisarAdd(){
        Estanteria est = new Estanteria();

        est.getModulo(0,0).agregarObjeto("Zapatilla");
        est.getModulo(0,0).agregarObjeto(true);
        est.getModulo(0,0).agregarObjeto(5);

        assertEquals(true,est.getModulo(0,0).getProductos().contains("Zapatilla"));
        assertEquals(false,est.getModulo(0,0).getProductos().contains(6));
    }

    @Test
    public void enumPrioridad(){
        assertEquals(3,Prioridad.ALTA.getValor());
    }
}
