package vaporware.practica2;

import java.util.LinkedList;

public class CambioMatrizForward extends Cambio {

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {
        //Creamos una matriz con con una fila mas que el cambio y una columna mas que monedas
        LinkedList<Integer>[][] matriz = new LinkedList[cambio + 1][monedas.length + 1];

        //Ejecutamos el algoritmo
        forward(matriz, monedas, cambio);

        //La solucion esta en la parte mas a la derecha de la matriz
        //en la primera fila que no sea null
        int fila = 0;
        for (fila = 0; matriz[fila][monedas.length] == null && fila < matriz.length; fila++) ;

        //Devolvemos el resultado como una matriz
        int[] output = new int[monedas.length];
        for (int i = 0; i < monedas.length; i++) {
            output[i] = matriz[fila][monedas.length].get(i);
        }
        return output;

    }

    @Override
    public String getTipo() {
        return "matriz forward";
    }

    private void printM(LinkedList<Integer>[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.format("%15s", matriz[i][j]);
            }
            System.out.println();
        }

    }

    public void forward(LinkedList<Integer>[][] matriz, int[] monedas, int cambio) {

        //Primero inicializamos la esquina inferior izquierda de la matriz
        matriz[cambio][0] = new LinkedList<Integer>();

        //Recorremos las columnas de izquierda a derecha
        for (int columna = 0; columna < monedas.length; columna++) {

            //Recorremos las filas de abajo a arriba
            for (int fila = cambio; fila >= 0; fila--) {

                //Si donde estamos no es null
                if (matriz[fila][columna] != null) {//Importante

                    //Iteramos sobre las monedas posibles
                    //Si tengo que devolver 5 con monedas de dos, n_monedas
                    //valdra 0, 1 y 2.
                    //Tambien tiene en cuenta las monedas anteriores. Si tengo que
                    //he usado 2 monedas de dos, si la siguiente es 3 pues solo pasara
                    //por el 0.
                    for (int n_monedas = 0; fila - n_monedas * monedas[columna] >= 0; n_monedas++) {

                        mirarHaciaDelante(fila, columna, n_monedas, matriz, monedas);

                    }
                }
            }
        }
    }

    private void mirarHaciaDelante(int fila, int columna, int n_monedas, LinkedList<Integer>[][] matriz, int[] monedas) {

        //Calculamos la fila de delante.
        //Si el numero de monedas es 0, será la misma que en la que estamos.
        //Si no, sera mas arriba.
        int filaDelante = fila - n_monedas * monedas[columna];

        //Clonamos la lista donde estamos
        LinkedList<Integer> nueva = (LinkedList<Integer>) matriz[fila][columna].clone();

        //Le ponemos la nueva moneda por el final
        nueva.addLast(n_monedas);

        //Si la siguiente es null o es mejor opcion que lo que hay la reemplazamos
        if (matriz[filaDelante][columna + 1] == null || monedasUsadas(nueva) < monedasUsadas(matriz[filaDelante][columna + 1])) {
            matriz[filaDelante][columna + 1] = nueva;
        }
    }

    private int monedasUsadas(LinkedList<Integer> matriz) {
        int out = 0;
        for (Integer i : matriz) {
            out += i;
        }
        return out;
    }


}
