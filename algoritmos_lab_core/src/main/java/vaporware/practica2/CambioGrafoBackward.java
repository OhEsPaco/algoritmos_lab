package vaporware.practica2;

import java.util.ArrayList;
import java.util.LinkedList;

public class CambioGrafoBackward extends Cambio {

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {


        NodoBackward inicial = new NodoBackward(null);


        //Ejecutamos el algoritmo
        backward(inicial, monedas, 0, cambio);

        int[] out = new int[monedas.length];

        for (int i = 0; i < inicial.getMonedas().size(); i++) {
            out[i] = inicial.getMonedas().get(i);
        }
        return out;

    }

    @Override
    public String getTipo() {
        return "grafo backward";
    }

    private void backward(NodoBackward nodo, int[] monedas, int etapa, int restante) {


        //El caso base es estar en la ultima moneda
        if (etapa == monedas.length) {
            //En el caso base los nodos contienen solo lo restante
            nodo.setRestante(restante);

        } else {

            //Caso recursivo

            //Generamos los hijos del nodo
            //Suponiendo que restante=4, monedas[2,3], y etapa=1,
            //n_monedas valdria primero 4 y despues 1.
            for (int n_monedas = 0; restante - n_monedas * monedas[etapa] >= 0; n_monedas++) {

                //Creamos el hijo correspondiente
                NodoBackward hijo = new NodoBackward(nodo);

                //Lo ponemos en la lista de hijos del padre
                nodo.addHijo(hijo);
                //Recursion
                backward(hijo, monedas, etapa + 1, restante - n_monedas * monedas[etapa]);

                //Le metemos el numero de monedas gastadas
                hijo.addMoneda(n_monedas);

                //Comprobamos si esa solucion es mejor que lo que tenemos ahora
                if (mejorSolucion(hijo, nodo)) {
                    nodo.setMonedas((LinkedList<Integer>) hijo.getMonedas().clone());
                    nodo.setRestante(hijo.getRestante());
                }
            }
        }

    }

    private boolean mejorSolucion(NodoBackward nueva, NodoBackward anterior) {

        //Si la anterior es vacia o la nueva tiene menos restante, la nueva es mejor
        if (anterior.getMonedas().isEmpty() || nueva.getRestante() < anterior.getRestante()) {
            return true;
        }

        //Contamos las monedas usadas
        int monedasNueva = 0;
        int monedasAnterior = 0;
        for (int i = 0; i < nueva.getMonedas().size(); i++) {
            monedasNueva += nueva.getMonedas().get(i);
        }

        for (int i = 0; i < anterior.getMonedas().size(); i++) {
            monedasAnterior += anterior.getMonedas().get(i);
        }

        //Si la nueva ha usado un menor numero de monedas, es mejor
        if (monedasNueva < monedasAnterior) {
            return true;
        }

        //En otro caso, false
        return false;

    }

    private class NodoBackward {
        private ArrayList<NodoBackward> hijos = new ArrayList<NodoBackward>();
        private NodoBackward padre = null;

        private LinkedList<Integer> monedas = new LinkedList<>();

        private int restante;

        public NodoBackward(NodoBackward padre) {
            this.padre = padre;

        }

        public int getRestante() {
            return restante;
        }

        public void setRestante(int restante) {
            this.restante = restante;
        }

        public void addHijo(NodoBackward hijo) {
            hijos.add(hijo);
        }

        public ArrayList<NodoBackward> getHijos() {
            return hijos;
        }

        public void addMoneda(int moneda) {
            this.monedas.addFirst(moneda);

        }

        public void setMonedas(LinkedList<Integer> monedas) {
            this.monedas = monedas;
        }

        public LinkedList<Integer> getMonedas() {
            return monedas;
        }


    }
}
