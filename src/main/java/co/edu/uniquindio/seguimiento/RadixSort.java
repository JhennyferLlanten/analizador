package co.edu.uniquindio.seguimiento;

import co.edu.uniquindio.modelo.TablaFrecuencias;

public class RadixSort {

    public static TablaFrecuencias ordenarRadixSort(TablaFrecuencias conteo) {

        ColaEnlazada[] Q = new ColaEnlazada[10];                                   // Una cola por cada dígito, de 0 a 9
        for (int i = 0; i < 10; i++) {
            Q[i] = new ColaEnlazada();
        }

        int total = conteo.cantidad;

        int maximo = 0;
        for (int i = 0; i < total; i++) {                                           // guarda la frecuencia más alta
            if (maximo < conteo.frecuencias[i]) {
                maximo = conteo.frecuencias[i];
            }
        }
        int numeroDigitos = String.valueOf(maximo).length();                        // cuántas pasadas hacer: los dígitos del máximo

        int[] posicionesOrdenadas = new int[total];                                 // El valor i es la posicion de las palabras y frecuencias, en el orden actual
        for (int i = 0; i < total; i++) {
            posicionesOrdenadas[i] = i;
        }

        for (int pasada = 1; pasada <= numeroDigitos; pasada++) {
            for (int j = 0; j < total; j++) {                                       // reparte: cada palabra va a la cola de su dígito
                int posicion = posicionesOrdenadas[j];
                int digito = obtenerRadical(conteo.frecuencias[posicion], pasada);
                Q[digito].encolar(posicion);                                        // encola la posición no la frecuencia
            }

            int lugar = 0;                                                          // saca las colas de mayor a menor y reordena las posiciones
            for (int k = 9; k >= 0; k--) {
                while (!Q[k].estaVacia()) {
                    posicionesOrdenadas[lugar] = Q[k].decolar();
                    lugar++;
                }
            }
        }

        String[] palabrasOrdenadas = new String[total];                             // arma la tabla nueva leyendo la original en el orden de posicionesOrdenadas
        int[] frecuenciasOrdenadas = new int[total];
        for (int i = 0; i < total; i++) {
            int posicion = posicionesOrdenadas[i];
            palabrasOrdenadas[i] = conteo.palabras[posicion];
            frecuenciasOrdenadas[i] = conteo.frecuencias[posicion];
        }
        return new TablaFrecuencias(palabrasOrdenadas, frecuenciasOrdenadas, total);
    }

    private static int obtenerRadical(int numero, int radical) {                    // retorna el dígito número "radical" (1 = unidades, 2 = decenas)
        return (int) (numero / Math.pow(10, radical - 1)) % 10;
    }
}
