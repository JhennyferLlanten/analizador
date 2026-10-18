package co.edu.uniquindio;

import java.io.IOException;

public class PruebaPdf {
    public static void main(String[] args) throws IOException {
        String ruta = "../Articulos Proyecto Algoritmos/9. AI technologies for education Recent research  future directions.pdf";

        String texto = LectorPdf.leerPrimeraPagina(ruta);

        System.out.println("Letras leidas: " + texto.length());
        System.out.println("----- texto de la primera pagina -----");
        System.out.println(texto);
        System.out.println("----- abstract extraido -----");
        System.out.println("[" + ExtractorArticulos.extraerAbstract(texto) + "]");
    }
}
