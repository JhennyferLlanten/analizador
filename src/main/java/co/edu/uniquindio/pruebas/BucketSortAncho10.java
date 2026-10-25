package co.edu.uniquindio.pruebas;

import co.edu.uniquindio.modelo.TablaFrecuencias;

public class BucketSortAncho10 {

    public static TablaFrecuencias ordenarBucketSortAncho10(TablaFrecuencias conteo) {
        int total = conteo.cantidad;

        int maximo = 0;
        for (int i = 0; i < total; i++) {                                           // guarda la frecuencia más alta
            if (maximo < conteo.frecuencias[i]) {
                maximo = conteo.frecuencias[i];
            }
        }

        int ancho = 10;                                                             // cuántas frecuencias cubre cada cubeta
        int numeroCubetas = (maximo - 1) / ancho + 1;                               // 9 cubetas
        int[][] cubetas = new int[numeroCubetas][total];                            // cada cubeta guarda posiciones de palabras
        int[] tamanos = new int[numeroCubetas];                                     // cuántas posiciones tiene guardada cada cubeta

        for (int i = 0; i < total; i++) {                                           // Cada palabra va a la cubeta de su rango
            int cubeta = (conteo.frecuencias[i] - 1) / ancho;
            cubetas[cubeta][tamanos[cubeta]] = i;                                   //Guarda la posicion en la cubeta correspondiente
            tamanos[cubeta]++;
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
