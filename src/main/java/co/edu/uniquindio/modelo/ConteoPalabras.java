package co.edu.uniquindio.modelo;

public class ConteoPalabras {

    public String[] palabras;
    public int[] frecuencias;
    public int cantidad;                    //cuantas palabras distintas hay

    public ConteoPalabras(String[] palabras, int[] frecuencias, int cantidad) {
        this.palabras = palabras;
        this.frecuencias = frecuencias;
        this.cantidad = cantidad;
    }

    public int frecuenciaDe(String palabra) {
        for (int i = 0; i < cantidad; i++) {
            if (palabras[i].equals(palabra)) {
                return frecuencias[i];
            }
        }
        return 0;
    }
}
