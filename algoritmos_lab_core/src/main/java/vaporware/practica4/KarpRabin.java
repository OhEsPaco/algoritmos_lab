package vaporware.practica4;

import vaporware.utilidades.IO;

import java.io.IOException;

public class KarpRabin {

    public static int ejecutar(String archivo, String patron, int porcentaje) throws IOException {
        String texto=IO.leerArchivo(archivo);

        if(porcentaje>100){
            porcentaje=100;
        }else if(porcentaje<0){
            porcentaje=20;
        }
        int longitud=Math.round((porcentaje/100f)*texto.length());
        return algoritmo(patron, texto, longitud);
    }

    private static int algoritmo(String patron, String texto, int longitud) {
        int longitudPatron = patron.length();
        int aciertos = 0;
        for (int posicionTexto = 0; posicionTexto <= longitud - longitudPatron; posicionTexto++) {
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
