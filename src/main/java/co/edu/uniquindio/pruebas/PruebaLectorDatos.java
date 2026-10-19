package co.edu.uniquindio.pruebas;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.LectorDatos;

import java.io.IOException;

public class PruebaLectorDatos {
    public static void main(String[] args) throws IOException {
        Articulo[] articulos = LectorDatos.leerArticulos();

        System.out.println("Articulos leidos: " + articulos.length);

        Articulo siete = articulos[6];
        System.out.println("Numero: " + siete.numero);
        System.out.println("Titulo: " + siete.titulo);
        System.out.println("Abstract (primeras letras): " + siete.resumen.substring(0, 80));

        int problemas = 0;
        for (int i = 0; i < articulos.length; i++) {
            if (articulos[i].numero != i + 1 || articulos[i].titulo.length() == 0 || articulos[i].resumen.length() == 0) {
                System.out.println("PROBLEMA en el articulo de la posicion " + i);
                problemas++;
            }
        }
        if (problemas == 0) {
            System.out.println("Los 20 articulos tienen numero, titulo y abstract");
        }
    }
}
