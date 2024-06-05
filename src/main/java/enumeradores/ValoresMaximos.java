package enumeradores;

public enum ValoresMaximos {
    MAXIMO_ESTANTERIAS (28),
    MAXIMO_NIVELES (3),
    MAXIMO_MODULOS (11),
    MAXIMO_X_CALZADO (8),
    MAXIMO_X_INDUMENTARIA(4);

    private int valor;

    private ValoresMaximos(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
