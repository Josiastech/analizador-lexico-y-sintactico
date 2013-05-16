/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.FileReader;

/**
 *
 * @author Josias
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    Main(String path) {
        parser ThisParser;
        Yylex ThisScanner;
        //String path="Entrada.txt";
        try {
            ThisScanner = new Yylex(new FileReader(path));
            ThisParser = new parser(ThisScanner);
            ThisParser.parse();

            Interfaz.rotular("Fin del Analisis");
        } catch (Exception e) {
            {
                Interfaz.rotular("Error en el proceso " + e.toString());
            }
        }

    }
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Interfaz I = new Interfaz();
        I.setVisible(true);
    }
}
