package vaporware.practica4;


public class KnuthMorrisPratt {


    public static int ejecutar(String texto, String patron) {
        int ocurrencias = 0;
        if (patron.length() > 0 && texto.length() >= patron.length()) {

            int[] fallo = preproceso(patron);
            ocurrencias = algoritmo(patron, texto, fallo);
        }
        return ocurrencias;

    }

    public static int algoritmo(String patron, String texto, int[] fallo) {

        int aciertos = 0;
        int posicionTexto = 0;
        int posicionEnPatron = 0;

        //Recorremos el texto de
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
                    if (posicionEnPatron > 0) {
                        posicionEnPatron = fallo[posicionEnPatron];
                    }
                }
            }
        }

        return aciertos;
    }


    public static int[] preproceso(String patron) {
        int[] fallo = new int[patron.length()];
        int j = 0;
        fallo[0] = -1;
        for (int i = 2; i < patron.length() && patron.length() > 1; ) {
            if (patron.charAt(i - 1) == patron.charAt(j)) {
                j++;
                fallo[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = fallo[j];
                } else {
                    fallo[i] = 0;
                    i++;
                }
            }
        }
        return fallo;
    }


}
