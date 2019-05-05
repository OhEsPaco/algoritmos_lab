package vaporware.practica4;

import vaporware.utilidades.IO;

import java.io.IOException;

public class Naive {

    public static int ejecutar(String archivo, String patron) throws IOException {
        return naive(IO.leerArchivo(archivo),patron);
    }

    private static int naive(String texto,String patron) {
        int aciertos = 0;
        if (patron.length() > 0 && texto.length() >= patron.length()) {

            for(int posicionTexto=0;texto.length() - posicionTexto >= patron.length();posicionTexto++){

                if (texto.charAt(posicionTexto) == patron.charAt(0)) {
                    int caracteresIguales = 1;
                    for(int posicionEnPatron=1;posicionEnPatron<patron.length();posicionEnPatron++){
                        if(texto.charAt(posicionEnPatron+posicionTexto)==patron.charAt(posicionEnPatron)){
                            caracteresIguales++;
                        }
                    }
                    if (caracteresIguales == patron.length()) {
                        aciertos++;
                    }
                }

            }
        }
        return aciertos;
    }

}
