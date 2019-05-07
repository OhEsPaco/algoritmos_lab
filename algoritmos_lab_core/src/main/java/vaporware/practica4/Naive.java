package vaporware.practica4;

public class Naive {

    public static int ejecutar(String texto, String patron) {
        return algoritmo(texto, patron);
    }

    private static int algoritmo(String texto, String patron) {
        int aciertos = 0;

        //Recorremos el texto de izquierda a derecha
        for (int posicionTexto = 0; texto.length() - posicionTexto >= patron.length(); posicionTexto++) {

            //Si el caracter es igual al primero del patron pasamos a analizar el resto
            if (texto.charAt(posicionTexto) == patron.charAt(0)) {
                int caracteresIguales = 1;
                for (int posicionEnPatron = 1; posicionEnPatron < patron.length(); posicionEnPatron++) {
                    if (texto.charAt(posicionEnPatron + posicionTexto) == patron.charAt(posicionEnPatron)) {
                        caracteresIguales++;
                    }
                }
                //Si el numero de caracteres iguales es el mismo que la longitud del patron
                //es que hemos encontrado un acierto
                if (caracteresIguales == patron.length()) {
                    aciertos++;
                }
            }

        }

        return aciertos;
    }

}
