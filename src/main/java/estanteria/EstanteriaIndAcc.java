package estanteria;

import enumeradores.MedidasIndAcc;
import enumeradores.Prioridad;
import enumeradores.ValoresMaximos;
import enumeradores.VolumenDisponible;

public class EstanteriaIndAcc extends Estanteria {
    MedidasIndAcc medidasIndAcc;

    public EstanteriaIndAcc(Prioridad prioridad, MedidasIndAcc medidasIndAcc) {
        super(prioridad);
        this.medidasIndAcc = medidasIndAcc;
    }

    public MedidasIndAcc getMedidasIndAcc() {
        return medidasIndAcc;
    }

    public void setMedidasIndAcc(MedidasIndAcc medidasIndAcc) {
        this.medidasIndAcc = medidasIndAcc;
    }

    @Override
    public void crearPosicionesEnMaximos() {
        for (int modulo = 1; modulo <= ValoresMaximos.MAXIMO_MODULOS.getValor(); modulo++) {
            for (int nivel = 1; nivel <= ValoresMaximos.MAXIMO_NIVELES.getValor(); nivel++) {
                for (int x = 1; x <= ValoresMaximos.MAXIMO_X_INDUMENTARIA.getValor(); x++) {
                    Posicion posicion = new Posicion(modulo, nivel, x, VolumenDisponible.ALTO, super.getPrioridad());
                    super.getEstanteria().put(posicion, null);
                }
            }
        }
    }
}
