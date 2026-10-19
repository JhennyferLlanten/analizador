package co.edu.uniquindio.pruebas;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.TablaFrecuencias;
import co.edu.uniquindio.modelo.LectorDatos;
import co.edu.uniquindio.seguimiento.ContadorPalabras;

import java.io.IOException;

public class PruebaContador {
    public static void main(String[] args) throws IOException {
        Articulo[] articulos = LectorDatos.leerArticulos();
        TablaFrecuencias conteo = ContadorPalabras.contar(articulos);

        System.out.println("Palabras distintas: " + conteo.cantidad);
        System.out.println("ai: " + conteo.frecuenciaDe("ai"));
        System.out.println("education: " + conteo.frecuenciaDe("education"));
        System.out.println("learning: " + conteo.frecuenciaDe("learning"));

        int suma = 0;
        for (int k = 0; k < conteo.cantidad; k++) {
            suma = suma + conteo.frecuencias[k];
        }
        System.out.println("Suma de todas las frecuencias: " + suma);
    }
}
