package co.edu.uniquindio.pruebas;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.LectorDatos;
import co.edu.uniquindio.modelo.TablaFrecuencias;
import co.edu.uniquindio.seguimiento.BucketSort;
import co.edu.uniquindio.seguimiento.ContadorPalabras;
import co.edu.uniquindio.seguimiento.CountingSort;
import co.edu.uniquindio.seguimiento.RadixSort;

import java.io.IOException;

public class MedicionTiempos {
    public static void main(String[] args) throws IOException {
        Articulo[] articulos = LectorDatos.leerArticulos();
        TablaFrecuencias conteo = ContadorPalabras.contar(articulos);

        long antes = System.nanoTime();                                             // reloj en nanosegundos
        CountingSort.ordenarCountingSort(conteo);
        long despues = System.nanoTime();
        System.out.println("Counting Sort: " + (despues - antes) / 1000000.0 + " ms");        // 1 ms = 1.000.000 ns

        antes = System.nanoTime();
        RadixSort.ordenarRadixSort(conteo);
        despues = System.nanoTime();
        System.out.println("Radix Sort:    " + (despues - antes) / 1000000.0 + " ms");

        antes = System.nanoTime();
        BucketSort.ordenarBucketSort(conteo);
        despues = System.nanoTime();
        System.out.println("Bucket Sort:   " + (despues - antes) / 1000000.0 + " ms");
    }
}
