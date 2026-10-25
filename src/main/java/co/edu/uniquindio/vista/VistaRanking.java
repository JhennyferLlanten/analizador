package co.edu.uniquindio.vista;

import co.edu.uniquindio.modelo.Articulo;
import co.edu.uniquindio.modelo.LectorDatos;
import co.edu.uniquindio.modelo.TablaFrecuencias;
import co.edu.uniquindio.seguimiento.ContadorPalabras;
import co.edu.uniquindio.seguimiento.EjecutorOrdenamientos;
import co.edu.uniquindio.seguimiento.ResultadoOrdenamiento;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

public class VistaRanking {

    public static void main(String[] args) throws IOException {
        Articulo[] articulos = LectorDatos.leerArticulos();
        TablaFrecuencias conteo = ContadorPalabras.contar(articulos);

        ResultadoOrdenamiento counting = EjecutorOrdenamientos.ejecutarCountingSort(conteo);
        ResultadoOrdenamiento radix = EjecutorOrdenamientos.ejecutarRadixSort(conteo);
        ResultadoOrdenamiento bucket = EjecutorOrdenamientos.ejecutarBucketSort(conteo);

        JPanel rankings = new JPanel(new GridLayout(1, 3, 10, 0));                  //una fila con tres columnas, una por algoritmo
        rankings.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));         //margen borde de la ventana
        rankings.add(crearPanel("Counting Sort", counting));
        rankings.add(crearPanel("Radix Sort", radix));
        rankings.add(crearPanel("Bucket Sort", bucket));

        JFrame ventana = new JFrame("Ranking de palabras");
        ventana.add(rankings);
        ventana.setSize(900, 400);
        ventana.setLocationRelativeTo(null);                                         //centra la ventana en la pantalla
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                      //al cerrar la ventana termina el programa
        ventana.setVisible(true);
    }

    public static JPanel crearPanel(String nombre, ResultadoOrdenamiento resultado) {
        String[] columnas = {"Posición", "Palabra", "Frecuencia"};
        Object[][] datos = new Object[15][3];                                        //15 filas (el top 15) y 3 columnas
        for (int i = 0; i < 15; i++) {
            datos[i][0] = i + 1;
            datos[i][1] = resultado.tabla.palabras[i];
            datos[i][2] = resultado.tabla.frecuencias[i];
        }
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);                                                     //solo se ve: no se puede editar
        tabla.setIntercellSpacing(new Dimension(12, 0));                             //espacio entre el contenido de la celda y los bordes de los lados

        JLabel titulo = new JLabel(nombre, SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));               //espacio debajo del título
        JLabel tiempo = new JLabel("Tiempo de ejecución: " + resultado.milisegundos + " ms", SwingConstants.CENTER);
        tiempo.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        JPanel panel = new JPanel(new BorderLayout());                               //título arriba, tabla en el centro, tiempo abajo
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(tiempo, BorderLayout.SOUTH);
        return panel;
    }
}
