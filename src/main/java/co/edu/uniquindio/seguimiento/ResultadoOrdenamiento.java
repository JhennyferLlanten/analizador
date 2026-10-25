package co.edu.uniquindio.seguimiento;

import co.edu.uniquindio.modelo.TablaFrecuencias;

public class ResultadoOrdenamiento {

    public TablaFrecuencias tabla;          //la tabla ordenada de mayor a menor frecuencia
    public double milisegundos;             //Tiempo de ejecución

    public ResultadoOrdenamiento(TablaFrecuencias tabla, double milisegundos) {
        this.tabla = tabla;
        this.milisegundos = milisegundos;
    }
}
