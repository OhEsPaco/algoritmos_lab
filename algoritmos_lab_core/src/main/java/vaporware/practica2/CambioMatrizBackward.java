package vaporware.practica2;

import java.util.LinkedList;

public class CambioMatrizBackward extends Cambio {

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {
        LinkedList<Integer>[][] matriz = new LinkedList[cambio + 1][monedas.length + 1];
//la ultima columna la reservamos para saber lo que dejamos de devolver. Lo ideal es 0
        backward(matriz, monedas, cambio, 0);

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

    private void backward(LinkedList<Integer>[][] matriz, int[] monedas, int fil, int col) {
        if (matriz[fil][col] == null) {//no calculado
            matriz[fil][col] = new LinkedList<Integer>();
            if (col == monedas.length) {
                matriz[fil][col].add(fil);//caso base. Le asignamos lo que queda por devolver
            } else {
                for (int numMon = 0; fil - numMon * monedas[col] >= 0; numMon++) {
                    backward(matriz, monedas, fil - numMon * monedas[col], col + 1);
                    LinkedList<Integer> nueva = (LinkedList<Integer>) matriz[fil - numMon * monedas[col]][col + 1].clone();
                    nueva.addFirst(numMon);
                    if (mejorB(nueva, matriz[fil][col])) {
                        matriz[fil][col] = nueva;
                    }
                }
            }

        }
    }

    private boolean mejorB(LinkedList<Integer> nueva, LinkedList<Integer> vieja) {
        boolean mejor = (vieja.isEmpty() || nueva.getLast() < vieja.getLast());
        if (!mejor && nueva.getLast() == vieja.getLast()) {
            Integer n = nueva.getLast();
            Integer v = vieja.getLast();
            nueva.removeLast();
            vieja.removeLast();//quito la ultima que es lo que dejo de devolver
            mejor = mejor(nueva, vieja);
            nueva.addLast(n);
            vieja.addLast(v);//las dejo como estaban
        }
        return mejor;

    }

    private boolean mejor(LinkedList<Integer> a, LinkedList<Integer> b) {
        return hayMonedas(a) < hayMonedas(b);
    }

    public int hayMonedas(LinkedList<Integer> a) {
        int h = 0;
        for (int n = 0; n < a.size(); n++) {
            h = h + a.get(n);
        }
        return h;
    }

}
