package co.edu.uniquindio.seguimiento;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.TablaFrecuencias;
import co.edu.uniquindio.procesamientoTexto.Preprocesador;

import java.util.ArrayList;

public class ContadorPalabras {

    public static TablaFrecuencias contar(Articulo[] articulos) {
        ArrayList<String> todas = new ArrayList<>();                    //todas las palabras de los 20 abstracts, con repetidas
        for (int i = 0; i < articulos.length; i++) {
            ArrayList<String> palabrasArticulo = Preprocesador.obtenerPalabras(articulos[i].resumen);
            for (int j = 0; j < palabrasArticulo.size(); j++) {
                todas.add(palabrasArticulo.get(j));
            }
        }

        String[] palabras = new String[todas.size()];                   //como maximo hay tantas palabras distintas como palabras en total
        int[] frecuencias = new int[todas.size()];
        int cantidad = 0;                                               //cantidad de palabras nuevas

        for (int j = 0; j < todas.size(); j++) {
            String palabra = todas.get(j);

            int posicion = -1;                                          //-1 significa que la palabra todavia no esta
            for (int k = 0; k < cantidad; k++) {
                if (palabras[k].equals(palabra)) {
                    posicion = k;
                    break;                                              //ya la encontro, devuelve la posición y deja de buscar
                }
            }

            if (posicion == -1) {
                palabras[cantidad] = palabra;                           //la palabra no esta, la agrega al final de la lista y la cuenta
                frecuencias[cantidad] = 1;
                cantidad++;                                             //aumenta en 1 la cantidad de palabras nuevas
            } else {
                frecuencias[posicion]++;                                //ya existe la palabra, fecuencia en la misma posición aumenta en 1
            }
        }
        return new TablaFrecuencias(palabras, frecuencias, cantidad);     //retorna la lista de palabras, la lista de frecuencia y la cantidad de palabras
    }
}
