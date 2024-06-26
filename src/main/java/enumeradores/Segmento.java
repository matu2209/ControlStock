package enumeradores;

public enum Segmento {
    ADULTOS (1),
    NINIOS (5);

    private int valor;

    Segmento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
