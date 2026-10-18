package co.edu.uniquindio;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class LectorPdf {

    public static String leerPrimeraPagina(String ruta) throws IOException {
        PDDocument documento = Loader.loadPDF(new File(ruta));
        PDFTextStripper lector = new PDFTextStripper();
        lector.setStartPage(1);
        lector.setEndPage(1);
        String texto = lector.getText(documento);
        documento.close();
        return texto;
    }
}
