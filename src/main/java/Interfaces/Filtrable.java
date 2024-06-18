package Interfaces;

import java.util.Collection;

public interface Filtrable<F> { //se utiliza para filtrar con un parametro en comun
    boolean filter(F parametroFiltrado);
}

