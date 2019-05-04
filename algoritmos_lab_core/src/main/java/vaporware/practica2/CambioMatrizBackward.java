package vaporware.practica2;

import vaporware.utilidades.Utilidades;

import java.util.LinkedList;

public class CambioMatrizBackward extends Cambio {

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {
        //Creamos una matriz con con una fila mas que el cambio y una columna mas que monedas
        LinkedList<Integer>[][] matriz = new LinkedList[cambio + 1][monedas.length + 1];

        //Ejecutamos el algoritmo
        backward(matriz, monedas, cambio, 0);

        //Cogemos la solucion. Esta en la esquina inferior izquierda
        int[] output = new int[monedas.length];
        for (int i = 0; i < monedas.length; i++) {
            output[i] = matriz[cambio][0].get(i);
        }
        return output;

    }

    @Override
    public String getTipo() {
        return "matriz backward";
    }

    private void backward(LinkedList<Integer>[][] matriz, int[] monedas, int fila, int columna) {

        //Si ya hemos pasado por aqui lo ignoramos
        if (matriz[fila][columna] == null) {

            //Como es null primero tenemos que inicializarlo
            matriz[fila][columna] = new LinkedList<Integer>();

            //El caso base es estar en la ultima columna
            if (columna == monedas.length) {

                //En el caso base la lista contiene lo que falta por devolver
                matriz[fila][columna].add(fila);
                Utilidades.printM(matriz);
                System.out.println();

            } else {

                //Caso recursivo

                //La fila representa lo que queda por devolver
                //Asi que suponiendo que fila=4, monedas[2,3], y columna=1,
                //n_monedas valdria primero 4 y despues 1.
                for (int n_monedas = 0; fila - n_monedas * monedas[columna] >= 0; n_monedas++) {

                    //Recursion
                    backward(matriz, monedas, fila - n_monedas * monedas[columna], columna + 1);

                    //Copiamos el calculado en la recursion
                    LinkedList<Integer> nueva = (LinkedList<Integer>) matriz[fila - n_monedas * monedas[columna]][columna + 1].clone();

                    //Le metemos el numero de monedas gastadas
                    nueva.addFirst(n_monedas);

                    //Comprobamos si esa solucion es mejor que lo que tenemos ahora
                    if (mejorSolucion(nueva, matriz[fila][columna])) {
                        matriz[fila][columna] = nueva;
                        Utilidades.printM(matriz);
                        System.out.println();
                    }
                }
            }

        }
    }

    private boolean mejorSolucion(LinkedList<Integer> nueva, LinkedList<Integer> anterior) {

        //Si la anterior es vacia o la nueva tiene menos restante, la nueva es mejor
        if(anterior.isEmpty() || nueva.getLast() < anterior.getLast()){
            return true;
        }

        //Contamos las monedas usadas
        int monedasNueva=0;
        int monedasAnterior=0;
        for(int i=0;i<nueva.size()-1;i++){
            monedasNueva+=nueva.get(i);
        }

        for(int i=0;i<anterior.size()-1;i++){
            monedasAnterior+=anterior.get(i);
        }

        //Si la nueva ha usado un menor numero de monedas, es mejor
        if(monedasNueva<monedasAnterior){
            return true;
        }

        //En otro caso, false
        return false;

    }

}
