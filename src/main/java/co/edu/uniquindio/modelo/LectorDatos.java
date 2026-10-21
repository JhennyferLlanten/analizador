package co.edu.uniquindio.modelo;

import co.edu.uniquindio.extraccion.ExtractorArticulos;
import co.edu.uniquindio.procesamientoTexto.BuscadorTexto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LectorDatos {

    public static Articulo[] leerArticulos() throws IOException {
        Articulo[] articulos = new Articulo[20];             //lista de articulos
        BufferedReader lector = new BufferedReader(new FileReader("src/main/resources/articulos.txt", StandardCharsets.UTF_8));   //abre el archivo para leerlo linea por linea

        int i = 0;
        String linea = lector.readLine();                   //lee la primera linea (primer articulo)
        while (linea != null) {                             //cuando ya no hay mas lineas, readLine entrega null
            int posicionPrimerTab = BuscadorTexto.buscarPatron(linea, "\t", 0);
            int posicionSegundoTab = BuscadorTexto.buscarPatron(linea, "\t", posicionPrimerTab+ 1);
            int numero = ExtractorArticulos.obtenerNumero(linea);

            String titulo = linea.substring(posicionPrimerTab + 1, posicionSegundoTab);
            String resumen = linea.substring(posicionSegundoTab + 1);

            articulos[i] = new Articulo(numero, titulo, resumen);
            i++;
            linea = lector.readLine();                      //pasa a la siguiente linea
        }
        lector.close();
        return articulos;
    }
}
