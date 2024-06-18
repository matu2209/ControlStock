package Interfaces;

public interface Asignable<A> { //se va a poder asignar un objeto a una posicin, un colaborador a una orden de pickeo o almacenamiento, un
    void asignar(A objetoAsignable);
    A getAsignado();
}
