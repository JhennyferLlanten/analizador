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

    public static String extraerAbstract(String texto) {
        int inicio = encontrarInicioAbstract(texto);
        if (inicio == -1) {                 //no esta la palabra Abstract
            return "";
        }

        int fin = encontrarFinAbstract(texto, inicio);
        if (fin == -1) {                    //no hay marca de final
            return "";
        }

        while (fin > inicio && esSeparador(texto.charAt(fin - 1))) {        //quita los separadores del final
            fin--;
        }
        return limpiarAbstract(texto.substring(inicio, fin));
    }

    public static String limpiarAbstract(String abstractCrudo) {
        StringBuilder limpio = new StringBuilder();         //aqui se van agregando las letras que se quedan
        int tamano = abstractCrudo.length();
        int i = 0;

        while (i < tamano) {
            char c = abstractCrudo.charAt(i);

            if (c == '-' && i + 1 < tamano && esSaltoDeLinea(abstractCrudo.charAt(i + 1))) {
                i++;                                        //salta el guion
                while (i < tamano && esSaltoDeLinea(abstractCrudo.charAt(i))) {
                    i++;                                    //salta el salto de linea: la palabra queda unida
                }
            } else if (esEspacioBlanco(c)) {                // si la nueva letra es un espacio, tabulador o un salto de línea
                if (limpio.length() > 0 && limpio.charAt(limpio.length() - 1) != ' ') {     //si hay texto y la ultima letra no es espacio
                    limpio.append(' ');                     //un solo espacio, sin repetir
                }
                i++;
            } else {
                limpio.append(c);
                i++;
            }
        }
        return limpio.toString();
    }

    public static boolean esSaltoDeLinea(char c) {
        return c == '\n' || c == '\r';
    }

    public static boolean esEspacioBlanco(char c) {
        return c == ' ' || c == '\t' || esSaltoDeLinea(c);
    }

    public static boolean esSeparador(char c) {
        return c == ' ' || c == ':' || c == '\n' || c == '\r' || c == '\t';
    }
}
