package vaporware.practica4;


public class KarpRabin {

    public static int ejecutar(String archivo, String patron) {
        return algoritmo(patron, archivo);
    }

    private static int algoritmo(String patron, String texto) {
        int longitudPatron = patron.length();
        int aciertos = 0;


            //Recorremos el texto de izquierda a derecha
            for (int posicionTexto = 0; posicionTexto <= texto.length() - longitudPatron; posicionTexto++) {

                //Tomamos la cadena entre la posicion actual y la longitud del patron
                String cadenaPosible = texto.substring(posicionTexto, posicionTexto + longitudPatron);

                //Si los hashes son iguales tenemos un posible acierto
                if (cadenaPosible.hashCode() == patron.hashCode()) {

                    //Comprobamos que la cadena posible sea igual que el patron
                    if (cadenaPosible.equals(patron)) {

                        //Hemos encontrado el patron
                        aciertos++;
                    }

                }
            }



        return aciertos;
    }

}
