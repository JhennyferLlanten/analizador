package co.edu.uniquindio.seguimiento;

public class nodoEntero {

    public int valor;
    public nodoEntero siguiente;

    nodoEntero(int a) {
        valor = a;
        siguiente = null;
    }

    nodoEntero() {
        siguiente = null;
        valor = 0;
    }
}
