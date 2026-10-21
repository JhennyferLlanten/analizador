package co.edu.uniquindio.seguimiento;

import co.edu.uniquindio.modelo.TablaFrecuencias;

public class CountingSort {

    public static TablaFrecuencias ordenarCountingSort(TablaFrecuencias conteo) {
        int total = conteo.cantidad;

        int maximo = 0;
        for (int i = 0; i < total; i++) {                      //guarda la frecuencia mas alta
            if (conteo.frecuencias[i] > maximo) {
                maximo = conteo.frecuencias[i];
            }
        }

        int[] palabrasPorFrecuencia = new int[maximo + 1];              //arreglo de frecuencia, cuantas palabras tienen una frecuencia
        for (int i = 0; i < total; i++) {
            palabrasPorFrecuencia[conteo.frecuencias[i]]++;             //suma 1 a la cantidad de palabras que tienen esta frecuencia
        }

        int[] inicio = new int[maximo + 1];                        //Para cada grupo de frecuencia, reserva la cant de espacios que tiene ese grupo
        int acumulado = 0;
        for (int v = maximo; v >= 1; v--) {                        //recorre de mayor a menor
            inicio[v] = acumulado;                                 //en que posicion inicia el grupo de la frecuencia
            acumulado = acumulado + palabrasPorFrecuencia[v];      //reserva los espacios y se mueve al siguiente espacio disponible
        }

        String[] palabrasOrdenadas = new String[total];                      //ordena las palabras por frecuencia de mayor a menor
        int[] frecuenciasOrdenadas = new int[total];
        for (int i = 0; i < total; i++) {
            int v = conteo.frecuencias[i];
            palabrasOrdenadas[inicio[v]] = conteo.palabras[i];             //busca en inicio el siguiente lugar libre de su grupo y pone la palabra
            frecuenciasOrdenadas[inicio[v]] = v;
            inicio[v]++;
        }
        return new TablaFrecuencias(palabrasOrdenadas, frecuenciasOrdenadas, total);
    }
}
