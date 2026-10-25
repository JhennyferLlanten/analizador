package co.edu.uniquindio.seguimiento;

import co.edu.uniquindio.modelo.TablaFrecuencias;

public class BucketSort {

    public static TablaFrecuencias ordenarBucketSort(TablaFrecuencias conteo) {
        int total = conteo.cantidad;

        int maximo = 0;
        for (int i = 0; i < total; i++) {                                           // guarda la frecuencia más alta
            if (maximo < conteo.frecuencias[i]) {
                maximo = conteo.frecuencias[i];
            }
        }

        int numeroCubetas = total;                                                  // n cubetas: una por cada palabra
        int[] cubetaDe = new int[total];                                            // a qué cubeta va cada palabra
        int[] tamanos = new int[numeroCubetas];                                     // cuántas palabras va a tener cada cubeta
        for (int i = 0; i < total; i++) {
            cubetaDe[i] = (conteo.frecuencias[i] - 1) * numeroCubetas / maximo;     // reparte las frecuencias de 1 a maximo entre las n cubetas
            tamanos[cubetaDe[i]]++;
        }

        int[][] cubetas = new int[numeroCubetas][];                                 // cada cubeta guarda posiciones de palabras
        for (int c = 0; c < numeroCubetas; c++) {
            cubetas[c] = new int[tamanos[c]];                                       // cada cubeta con el tamaño justo
        }

        int[] llenos = new int[numeroCubetas];                                      // cuántas posiciones lleva guardadas cada cubeta
        for (int i = 0; i < total; i++) {                                           // reparte: cada palabra va a su cubeta
            int c = cubetaDe[i];                                                    // que cubeta le corresponde
            cubetas[c][llenos[c]] = i;                                              //la guarda en el primer lugar vacio de la cubeta
            llenos[c]++;
        }

        for (int c = 0; c < numeroCubetas; c++) {                                   // ordena cada cubeta por dentro (inserción, de mayor a menor)
            for (int j = 1; j < tamanos[c]; j++) {
                int llave = cubetas[c][j];                                          // la posición que se va a acomodar
                int i = j - 1;
                while (i >= 0 && conteo.frecuencias[cubetas[c][i]] < conteo.frecuencias[llave]) {      //mientras lo que tengo sea menor a lo que llega
                    cubetas[c][i + 1] = cubetas[c][i];                              // corre una posición a la derecha
                    i--;
                }
                cubetas[c][i + 1] = llave;                                          // la deja en el espacio de la izquierda
            }
        }

        String[] palabrasOrdenadas = new String[total];                             // junta las cubetas y arma la tabla nueva
        int[] frecuenciasOrdenadas = new int[total];
        int lugar = 0;                                                              // lugar de la tabla nueva que se llena
        for (int c = numeroCubetas - 1; c >= 0; c--) {                              // de la cubeta de frecuencias altas a la de bajas
            for (int k = 0; k < tamanos[c]; k++) {                                  //cada cubeta internamente
                int posicion = cubetas[c][k];
                palabrasOrdenadas[lugar] = conteo.palabras[posicion];
                frecuenciasOrdenadas[lugar] = conteo.frecuencias[posicion];
                lugar++;
            }
        }
        return new TablaFrecuencias(palabrasOrdenadas, frecuenciasOrdenadas, total);
    }
}
