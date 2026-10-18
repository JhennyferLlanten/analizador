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

        String elsevier = "ARTICLE INFO\nKeywords: AI\nABSTRACT  \nSince artificial intelligence is";
        String mdpi = "Received: 28 March 2024 Abstract: The use of generative AI";
        String emerald = "Abstract\nPurpose - The purpose of this study";
        String sinMarca = "Este texto no tiene la palabra";

        mostrarInicio("texto1", texto1);
        mostrarInicio("elsevier", elsevier);
        mostrarInicio("mdpi", mdpi);
        mostrarInicio("emerald", emerald);
        mostrarInicio("sinMarca", sinMarca);

        String elsevierCompleto = "ARTICLE INFO\nKeywords: AI\nABSTRACT  \nSince artificial intelligence is everywhere.\n1. Introduction\nAI is used";
        String mdpiCompleto = "Received: 28 March 2024 Abstract: The use of generative AI in academia. Keywords: AI; education 1. Introduction Generative";
        String wileyCompleto = "Abstract Recent literature underscores the need. KEYWORDS AI competence 1. Introduction";
        String sinFin = "Abstract: texto que no tiene ninguna marca de final";

        mostrarAbstract("elsevierCompleto", elsevierCompleto);
        mostrarAbstract("mdpiCompleto", mdpiCompleto);
        mostrarAbstract("wileyCompleto", wileyCompleto);
        mostrarAbstract("sinFin", sinFin);

        String sinEspacio = "Abstract: Texto importante.Keywords: AI";
        String variosSeparadores = "Abstract Texto con varios.  \n Keywords: AI";

        mostrarExtraccion("elsevierCompleto", elsevierCompleto);
        mostrarExtraccion("mdpiCompleto", mdpiCompleto);
        mostrarExtraccion("wileyCompleto", wileyCompleto);
        mostrarExtraccion("sinEspacio", sinEspacio);
        mostrarExtraccion("variosSeparadores", variosSeparadores);
        mostrarExtraccion("sinFin", sinFin);
        mostrarExtraccion("sinMarca", sinMarca);

        String conSaltos = "Abstract\nSince artificial intelligence (AI) is finding its way into more and\nmore areas of everyday life, improving the AI skills of non-experts is\nimportant. In this practi-\ntioners paper we study  two   things.\r\nThe end.\nKeywords: AI";
        mostrarExtraccion("conSaltos", conSaltos);
    }

    public static void mostrarExtraccion(String nombre, String texto) {
        String resultado = ExtractorArticulos.extraerAbstract(texto);
        if (resultado.length() == 0) {
            System.out.println(nombre + " -> no se pudo extraer");
        } else {
            System.out.println(nombre + " -> [" + resultado + "]");
        }
    }

    public static void mostrarAbstract(String nombre, String texto) {
        int inicio = ExtractorArticulos.encontrarInicioAbstract(texto);
        int fin = ExtractorArticulos.encontrarFinAbstract(texto, inicio);
        System.out.println(nombre + " -> inicio = " + inicio + ", fin = " + fin);
        if (fin != -1) {
            System.out.println("   abstract: [" + texto.substring(inicio, fin) + "]");
        }
    }

    public static void mostrarInicio(String nombre, String texto) {
        int inicio = ExtractorArticulos.encontrarInicioAbstract(texto);
        System.out.println(nombre + " -> inicio = " + inicio);
        if (inicio != -1) {
            System.out.println("   el abstract empieza con: [" + texto.substring(inicio, inicio + 12) + "]");
        }
    }
}
