package estanteria;

import enumeradores.*;

import java.util.Objects;

public class EstanteriaCalzado extends Estanteria{
    private static Integer autoId=1;
    private final Integer idEstanteria;

    public EstanteriaCalzado(Prioridad prioridad, Empresa empresa) {
        super(prioridad, empresa);
        this.idEstanteria=autoId++;
    }


    public Integer getIdEstanteria() {
        return idEstanteria;
    }

    @Override
    public void crearPosicionesEstanteria() {
        for (int modulo = 1; modulo <= ValoresMaximos.MAXIMO_MODULOS.getValor(); modulo++) {
            for (int nivel = 1; nivel <= ValoresMaximos.MAXIMO_NIVELES.getValor(); nivel++) {
                for (int x = 1; x <= ValoresMaximos.MAXIMO_X_CALZADO.getValor(); x++) {
                    Posicion posicion = new Posicion(modulo, nivel, x,this.getIdEstanteria(), super.getPrioridad());
                    super.getListaPosiciones().agregar(posicion);
                }
            }
        }
    }

    @Override
    public boolean filter(Prioridad parametroFiltrado) { //RETORNA TRUE SI LA PRIORIDAD DE LA ESTANTERIA ES LA MISMA QUE EL PARAMETRO DE FILTRADO
        return super.getPrioridad().equals(parametroFiltrado);
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return (this instanceof EstanteriaCalzado) ? this.getIdEstanteria().equals(parametroABuscar) : false;

    }


    @Override
    public int compareTo(Estanteria otra) {
        // Verificar que otra sea una estanteria de calzado
        if (otra instanceof EstanteriaCalzado) {
            // Comparar por idEstanteria
            return this.getIdEstanteria().compareTo(((EstanteriaCalzado) otra).getIdEstanteria());
        }
        // Si no es una estanteria calzado lanzamos una excepción
        throw new IllegalArgumentException("No se puede comparar la entanteria porque no es una Estanteria de calzado");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EstanteriaCalzado that = (EstanteriaCalzado) o;
        return Objects.equals(getIdEstanteria(), that.getIdEstanteria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdEstanteria());
    }

    @Override
    public String toString() {
        return "EstanteriaCalzado{" +
                "idEstanteria=" + idEstanteria +
                '}';
    }

}
