package co.edu.uniquindio;

public class Main {
    public static void main(String[] args) {
        String texto1 = "Keywords: AI literacy ABSTRACT Since artificial intelligence is finding its way";
        String texto2 = "Received: 28 March 2024 Abstract: The use of generative AI in academia";
        String texto3 = "intro abstract of the paper";

        System.out.println("Abstract en texto1: " + BuscadorTexto.buscarPatron(texto1, "Abstract", 0));
        System.out.println("Abstract en texto2: " + BuscadorTexto.buscarPatron(texto2, "Abstract", 0));
        System.out.println("Abstract en texto3: " + BuscadorTexto.buscarPatron(texto3, "Abstract", 0));
        System.out.println("ABSTRACT en texto3: " + BuscadorTexto.buscarPatron(texto3, "ABSTRACT", 0));
        System.out.println("Abstract no existe: " + BuscadorTexto.buscarPatron("hola mundo", "Abstract", 0));
        System.out.println("way en texto1: " + BuscadorTexto.buscarPatron(texto1, "way", 0));
        System.out.println("AI desde 11: " + BuscadorTexto.buscarPatron(texto1, "AI", 11));
    }
}
