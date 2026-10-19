package co.edu.uniquindio.seguimiento;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.ConteoPalabras;
import co.edu.uniquindio.procesamientoTexto.Preprocesador;

import java.util.ArrayList;

public class ContadorPalabras {

    public static ConteoPalabras contar(Articulo[] articulos) {
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
                    break;                                              //ya la encontro, deja de buscar
                }
            }

            if (posicion == -1) {
                palabras[cantidad] = palabra;                           //la palabra no esta, la agrega a la lista y la cuenta
                frecuencias[cantidad] = 1;
                cantidad++;
            } else {
                frecuencias[posicion]++;
            }
        }
        return new ConteoPalabras(palabras, frecuencias, cantidad);     //retorna la lista de palabras, la lista de frecuencia y la cantidad de palabras
    }
}
