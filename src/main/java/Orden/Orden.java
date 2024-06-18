package Orden;

import Productos.Producto;
import enumeradores.EstadoOrden;
import estanteria.Posicion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Orden {

    //ESTA CLASE solo trabajará con el producto manipulado para una posición determinada,
    // las cantidades manipuladas en esa posición, quién manipula la mercadería, la fecha y si fue
    // completada o no de manera que se pueda controlar de manera rigurosa quién estuvo modificando lo almacanado y saber por qué.
    // A su vez, permitirá separar las tareas de manipulación de productos a la hora  de generar las órdenes, de manera que quién lo realiza
    // pueda hacerlo de una manera más eficiente aumentando así la productividad de la tarea. Por ejemplo, se podrá trabajar (en caso
    // del almacenamiento), con 5 remitos en simultaneo, y filtrar aquellos articulos por estanteria, dividiendo por ejemplo
    // la tarea por estantería, y asignarle a los colaboradores que la realicen un grupo determinado de estanterías. Esto generará que pierdan menos tiempo
    // en el recorrido de todas las entanterías (solo se avocan a las estanterías que le corresponden)

    private Producto producto;
    private Integer cantidadProducto;
    private Integer posicion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaRealizacion;
    private Integer legajo;              //Legajo del colaborador que realiza la orden
    private EstadoOrden estado;

    public Orden(Producto producto, Integer cantidadProducto ) { //la orden se crea siempre EN_PROCESO
        this.producto = producto;
        this.cantidadProducto = cantidadProducto;
        this.posicion=null;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoOrden.EN_PROCESO;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fecha) {
        this.fechaCreacion = fecha;
    }

    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDateTime fecha) {
        this.fechaRealizacion = fecha;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orden orden = (Orden) o;
        return Objects.equals(getProducto(), orden.getProducto()) && Objects.equals(getCantidadProducto(), orden.getCantidadProducto()) && Objects.equals(getPosicion(), orden.getPosicion()) && Objects.equals(getFechaCreacion(), orden.getFechaCreacion()) && Objects.equals(getFechaRealizacion(), orden.getFechaRealizacion()) && Objects.equals(getLegajo(), orden.getLegajo()) && getEstado() == orden.getEstado();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducto(), getCantidadProducto(), getFechaCreacion(), getEstado());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Orden{" +
                "producto=" + producto +
                ", cantidadProducto=" + cantidadProducto +
                ", posicion=" + posicion +
                ", fechaCreacion=" + fechaCreacion.format(formatter)+
                ", fechaRealizacion=" + fechaRealizacion.format(formatter) +
                ", legajo=" + legajo +
                ", estado=" + estado +
                '}';
    }

    public abstract void finalizarOrden(Integer legajoRealizador, Integer posicion);
    public abstract void cancelarOrden();

}
