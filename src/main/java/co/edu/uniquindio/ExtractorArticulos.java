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

    public static boolean esSeparador(char c) {
        return c == ' ' || c == ':' || c == '\n' || c == '\r' || c == '\t';
    }
}
