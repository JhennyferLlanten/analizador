package co.edu.uniquindio.seguimiento;

import co.edu.uniquindio.modelo.TablaFrecuencias;

public class EjecutorOrdenamientos {

    public static ResultadoOrdenamiento ejecutarCountingSort(TablaFrecuencias conteo) {
        long antes = System.nanoTime();                                             // reloj en nanosegundos
        TablaFrecuencias ordenado = CountingSort.ordenarCountingSort(conteo);
        long despues = System.nanoTime();
        return new ResultadoOrdenamiento(ordenado, (despues - antes) / 1000000.0);  // 1 ms = 1.000.000 ns
    }

    public static ResultadoOrdenamiento ejecutarRadixSort(TablaFrecuencias conteo) {
        long antes = System.nanoTime();
        TablaFrecuencias ordenado = RadixSort.ordenarRadixSort(conteo);
        long despues = System.nanoTime();
        return new ResultadoOrdenamiento(ordenado, (despues - antes) / 1000000.0);
    }

    public static ResultadoOrdenamiento ejecutarBucketSort(TablaFrecuencias conteo) {
        long antes = System.nanoTime();
        TablaFrecuencias ordenado = BucketSort.ordenarBucketSort(conteo);
        long despues = System.nanoTime();
        return new ResultadoOrdenamiento(ordenado, (despues - antes) / 1000000.0);
    }
}
