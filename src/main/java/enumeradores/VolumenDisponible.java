package enumeradores;

public enum VolumenDisponible {
    COMPLETO(100.0),
    ALTO(80.0),
    MEDIO(50.0),
    VACIO(0.0);

    private final double capacidad;

    VolumenDisponible(double capacidad) {
        this.capacidad = capacidad;
    }

    public double getCapacidad() {
        return capacidad;
    }
}