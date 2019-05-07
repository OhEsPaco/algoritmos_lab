package vaporware.practica4;


public class KnuthMorrisPratt {


    public static int ejecutar(String texto, String patron) {

        return algoritmo(texto, patron);

    }

    public static int algoritmo(String texto, String patron) {

        int aciertos = 0;
        int posicionTexto = 0;
        int posicionEnPatron = 0;

        //Creamos la tabla de fallos
        int[] tablaDeFallos = preproceso(patron);
        //Recorremos el texto de izquierda a derecha
        while (texto.length() - posicionTexto >= patron.length()) {
            //Si llegamos al final del patron es un acierto
            if (posicionEnPatron == patron.length()) {
                aciertos++;
                posicionEnPatron = 0;
                posicionTexto++;
            } else {

                //Si el caracter en la posicion actual coincide con el del patron, avanzamos
                if (texto.charAt(posicionTexto + posicionEnPatron) == patron.charAt(posicionEnPatron)) {
                    posicionEnPatron++;
                } else {

                    //Retrocedo acorde a la tabla de fallos
                    posicionTexto = posicionTexto + posicionEnPatron - tablaDeFallos[posicionEnPatron];

                    //Comprobamos no estar en la posicion 0 del patron que equivale a -1
                    //antes de calcular la posicion tomando en cuenta la tabla de fallos
                    if (posicionEnPatron > 0) {
                        posicionEnPatron = tablaDeFallos[posicionEnPatron];
                    }
                }
            }
        }

        return aciertos;
    }


    private static int[] preproceso(String patron) {
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
