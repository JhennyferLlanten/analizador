package co.edu.uniquindio;

public class ExtractorArticulos {

    public static int encontrarInicioAbstract(String texto) {
        String marca = "Abstract";
        int posicionMarca = BuscadorTexto.buscarPatron(texto, marca, 0);

        if (posicionMarca == -1) {          //la palabra no esta
            return -1;
        }

        int inicio = posicionMarca + marca.length();        //Se ubica al terminar la palabra
        while (inicio < texto.length() && esSeparador(texto.charAt(inicio))) {
            inicio++;
        }
        return inicio;
    }

    public static int encontrarFinAbstract(String texto, int inicioAbstract) {
        String[] marcasFin = {"Keywords", "1. Introduction"};
        int posicionFin = -1;

        for (int i = 0; i < marcasFin.length; i++) {
            int posicionMarca = BuscadorTexto.buscarPatron(texto, marcasFin[i], inicioAbstract);
            if (posicionMarca != -1 && (posicionFin == -1 || posicionMarca < posicionFin)) {    //marca existe y (no hay posicion guardada o la nueva marca esta primero)
                posicionFin = posicionMarca;           //Guarda la posición donde termina el texto del abstract
            }
        }
        return posicionFin;
    }

    public static boolean esSeparador(char c) {
        return c == ' ' || c == ':' || c == '\n' || c == '\r' || c == '\t';
    }
}
