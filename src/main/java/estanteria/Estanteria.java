package estanteria;

public class Estanteria {

    private int id;
    private static int contId=0;
    private Modulo[][] posiciones;

    public Estanteria() {
        contId++;
        this.id=contId;
        this.posiciones = new Modulo[100][3];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 3; j++) {
                this.posiciones[i][j] = new Modulo();
            }
        }
    }

    public Modulo getModulo(int posicion, int modulo) {
        return this.posiciones[posicion][modulo];
    }

}
