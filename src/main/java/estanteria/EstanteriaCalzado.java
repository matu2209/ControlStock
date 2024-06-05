package estanteria;

import enumeradores.*;

public class EstanteriaCalzado extends Estanteria {
    MedidasCalzado medidasCalzado;

    public EstanteriaCalzado(Prioridad prioridad, MedidasCalzado medidasCalzado) {
        super(prioridad);
        this.medidasCalzado = medidasCalzado;
    }

    public MedidasCalzado getMedidasCalzado() {
        return medidasCalzado;
    }

    public void setMedidasCalzado(MedidasCalzado medidasCalzado) {
        this.medidasCalzado = medidasCalzado;
    }

    @Override
    public void crearPosicionesEnMaximos() {
        for (int modulo = 1; modulo <= ValoresMaximos.MAXIMO_MODULOS.getValor(); modulo++) {
            for (int nivel = 1; nivel <= ValoresMaximos.MAXIMO_NIVELES.getValor(); nivel++) {
                for (int x = 1; x <= ValoresMaximos.MAXIMO_X_CALZADO.getValor(); x++) {
                    Posicion posicion = new Posicion(modulo, nivel, x, VolumenDisponible.ALTO, super.getPrioridad());
                    super.getEstanteria().put(posicion, null);
                }
            }
        }
    }

}
