package enumeradores;

public enum VolumenDisponible {
    VACIO(100.0),
    ALTO(80.0),
    MEDIO(50.0),
    LLENO(0.0);

    private final double capacidad;

    VolumenDisponible(double capacidad) {
        this.capacidad = capacidad;
    }

    public double getCapacidad() {
        return capacidad;
    }
}