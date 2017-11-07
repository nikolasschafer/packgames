
package br.edu.ifc.riodosul.pweb.pessoa;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable, Comparable<Administrador> {
    
    @Override
    public int compareTo(Administrador o) {
        int saida = -2;
        if (super.getId() < o.getId()) {
            saida = -1;
        } else if (super.getId() == o.getId()) {
            saida = 0;
        } else {
            saida = 1;
        }
        return saida;
    }
    
}
