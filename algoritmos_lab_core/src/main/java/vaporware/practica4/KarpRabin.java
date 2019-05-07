package vaporware.practica4;

import vaporware.utilidades.IO;

import java.io.IOException;

public class KarpRabin {

    public static int ejecutar(String archivo, String patron) throws IOException {

        return algoritmo(patron, archivo);
    }

    private static int algoritmo(String patron, String texto) {
        int longitudPatron = patron.length();
        int aciertos = 0;
        for (int posicionTexto = 0; posicionTexto <= texto.length() - longitudPatron; posicionTexto++) {
            String cadenaPosible = texto.substring(posicionTexto, posicionTexto + longitudPatron);
            if (cadenaPosible.hashCode() == patron.hashCode()) {
                if (cadenaPosible.equals(patron)) {
                    aciertos++;
                }

            }
        }

        return aciertos;
    }

}
