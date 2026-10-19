package co.edu.uniquindio.pruebas;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.TablaFrecuencias;
import co.edu.uniquindio.modelo.LectorDatos;
import co.edu.uniquindio.seguimiento.ContadorPalabras;
import co.edu.uniquindio.seguimiento.CountingSort;

import java.io.IOException;

public class PruebaCountingSort {
    public static void main(String[] args) throws IOException {
        Articulo[] articulos = LectorDatos.leerArticulos();
        TablaFrecuencias conteo = ContadorPalabras.contar(articulos);
        TablaFrecuencias ordenado = CountingSort.ordenarCountingSort(conteo);

        System.out.println("Top 15 con Counting Sort:");
        for (int i = 0; i < 15; i++) {
            System.out.println((i + 1) + ". " + ordenado.palabras[i] + " - " + ordenado.frecuencias[i]);
        }

        boolean ordenadoDeMayorAMenor = true;
        boolean conservaFrecuencias = true;
        for (int i = 0; i < ordenado.cantidad; i++) {
            if (i > 0 && ordenado.frecuencias[i] > ordenado.frecuencias[i - 1]) {
                ordenadoDeMayorAMenor = false;
            }
            if (conteo.frecuenciaDe(ordenado.palabras[i]) != ordenado.frecuencias[i]) {
                conservaFrecuencias = false;
            }
        }
        System.out.println("Palabras: " + ordenado.cantidad + " (antes: " + conteo.cantidad + ")");
        System.out.println("Ordenado de mayor a menor: " + ordenadoDeMayorAMenor);
        System.out.println("Cada palabra conserva su frecuencia: " + conservaFrecuencias);
    }
}
