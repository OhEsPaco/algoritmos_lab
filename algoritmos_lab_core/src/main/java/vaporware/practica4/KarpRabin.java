package vaporware.practica4;

import vaporware.utilidades.IO;

import java.io.IOException;

public class KarpRabin {

    public static int ejecutar(String archivo, String patron) throws IOException {
        return algoritmo(patron, IO.leerArchivo(archivo));
    }

    private static int algoritmo(String patron, String texto) {
        int m = patron.length();
        int ocurrencias=0;
        for (int n = 0; n <= texto.length() - m; n++) {
            String aux = texto.substring(n, n + m);
            if (aux.hashCode() == patron.hashCode() && aux.equals(patron)){
                ocurrencias++;
            }
        }

        return ocurrencias;
    }

}
