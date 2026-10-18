package co.edu.uniquindio;

public class ExtractorArticulos {

    public static int encontrarInicioAbstract(String texto) {
        String[] marcasInicio = {"Abstract", "A B S T R A C T"};
        int posicionMarca = -1;
        int tamanoMarca = 0;

        for (int i = 0; i < marcasInicio.length; i++) {
            int posicion = BuscadorTexto.buscarPatron(texto, marcasInicio[i], 0);
            if (posicion != -1 && (posicionMarca == -1 || posicion < posicionMarca)) {      //la marca existe y (no hay una guardada o esta esta primero)
                posicionMarca = posicion;
                tamanoMarca = marcasInicio[i].length();         //guarda el largo de la marca que se encontro
            }
        }

        if (posicionMarca == -1) {          //ninguna marca esta
            return -1;
        }

        int inicio = posicionMarca + tamanoMarca;        //Se ubica al terminar la marca
        while (inicio < texto.length() && esSeparador(texto.charAt(inicio))) {
            inicio++;
        }
        return inicio;
    }

    public static int encontrarFinAbstract(String texto, int inicioAbstract) {
        String[] marcasFin = {"\nKeywords", "\nK E Y W O R D S", "1. Introduction"};      //Keywords debe empezar una linea nueva, asi no se confunde con la palabra dentro del texto
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
            } else if (esInvisible(c)) {                    //letra que no se ve: se descarta
                i++;
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
        return c == ' ' || c == '\t' || esSaltoDeLinea(c)
                || c == ' ' || c == ' ' || c == ' ' || c == ' ';        //otros espacios que traen algunos PDF
    }

    public static boolean esInvisible(char c) {
        return c == '​' || c == '﻿';          //espacio de ancho cero y marca de inicio de archivo
    }

    public static boolean esSeparador(char c) {
        return c == ':' || esEspacioBlanco(c) || esInvisible(c);
    }
}
