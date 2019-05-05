package vaporware.practica4;

import vaporware.utilidades.IO;

import java.io.IOException;

public class KnutMorrisPratt {


    public static int ejecutar(String archivo, String patron) throws IOException {
        return knuthMorrisPrat(IO.leerArchivo(archivo), patron);
    }

    public static int algoritmo(String patron, String texto, int[] fallo) {

        int aciertos = 0;
        int posicionTexto = 0;
        int posicionEnPatron = 0;
        while (texto.length() - posicionTexto >= patron.length()) {
            if (posicionEnPatron == patron.length()) {
                aciertos++;
                posicionEnPatron = 0;
                posicionTexto++;
            } else {
                if (texto.charAt(posicionTexto + posicionEnPatron) == patron.charAt(posicionEnPatron)) {
                    posicionEnPatron++;
                } else {
                    posicionTexto = posicionTexto + posicionEnPatron - fallo[posicionEnPatron];
                    if (posicionEnPatron > 0){
                        posicionEnPatron = fallo[posicionEnPatron];
                    }
                }
            }
        }
        return aciertos;
    }

    public static int[] preproceso(String patron) {
        int[] fallo = new int[patron.length()];
        int i = 2;
        int j = 0;
        fallo[0] = -1;
        if (patron.length() > 1) {
            fallo[1] = 0;
            while (i < patron.length()) {
                if (patron.charAt(i - 1) == patron.charAt(j)) {
                    j++;
                    fallo[i] = j;
                    i++;
                } else {
                    if (j > 0) j = fallo[j];
                    else {
                        fallo[i] = 0;
                        i++;
                    }
                }
            }
        }
        return fallo;
    }

    public static int knuthMorrisPrat(String texto, String patron) {
        int ocurrencias = 0;
        if (patron.length() > 0 && texto.length() >= patron.length()) {
            int[] fallo = new int[patron.length()];
            fallo = preproceso(patron);//matriz de fallos
            ocurrencias = algoritmo(patron, texto, fallo);
        }
        return ocurrencias;
    }

}
