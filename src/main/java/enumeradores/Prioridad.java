package enumeradores;

public enum Prioridad {
    BAJA(0),
    REGULAR(1),
    MEDIA(2),
    ALTA(3);

    private int valor;

    Prioridad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
