package co.edu.uniquindio;

import java.io.IOException;

public class PruebaPdf {
    public static void main(String[] args) throws IOException {
        String ruta = "articulosProyecto/7. Human-centred learning analytics and AI in education  A systematic review.pdf";

        String texto = LectorPdf.leerPrimeraPagina(ruta);

        System.out.println("Titulo (metadatos): " + LectorPdf.leerTitulo(ruta));
        System.out.println("Letras leidas: " + texto.length());
        System.out.println("----- texto de la primera pagina -----");
        System.out.println(texto);
        System.out.println("----- abstract extraido -----");
        System.out.println("[" + ExtractorArticulos.extraerAbstract(texto) + "]");
    }
}
