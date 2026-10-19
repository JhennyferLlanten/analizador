package co.edu.uniquindio.pruebas;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.LectorDatos;
import co.edu.uniquindio.procesamientoTexto.Preprocesador;

import java.io.IOException;
import java.util.ArrayList;

public class PruebaPreprocesador {
    public static void main(String[] args) throws IOException {
        System.out.println("Ejemplo: " + Preprocesador.obtenerPalabras("The use of AI, in education. Human-centred (2023) isn't it?"));

        Articulo[] articulos = LectorDatos.leerArticulos();
        ArrayList<String> palabrasArticulo7 = Preprocesador.obtenerPalabras(articulos[6].resumen);
        System.out.println("Articulo 7: " + palabrasArticulo7.size() + " palabras. Las primeras 15: " + palabrasArticulo7.subList(0, 15));

    }
}
