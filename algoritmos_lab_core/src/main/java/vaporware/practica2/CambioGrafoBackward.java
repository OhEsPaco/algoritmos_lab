package vaporware.practica2;

import java.util.ArrayList;
import java.util.LinkedList;

import static vaporware.practica2.Cambio.MONEDA_INICIAL;

public class CambioGrafoBackward extends Cambio {

    public static final int COSTE_INICIAL = 999999;
    public static final int MAX_VERTICES = 200;

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {

        //Nodo inicial del grafo
        NodoBackward primerNodo = new NodoBackward(MONEDA_INICIAL, cambio, 0);

        //Generamos el grafo
        Grafo<NodoBackward, Integer> mapa = generarMapa(primerNodo, monedas, cambio);

        //Lista para guardar los nodos visitados
        ArrayList<NodoBackward> visitados = new ArrayList<>();

        //Ejecutamos el algoritmo
        backward(primerNodo, visitados, mapa);

        //Recorremos la solucion
        NodoBackward act = primerNodo;
        int[] output = new int[monedas.length];
        while (act.getVieneDe() != null) {
            act = act.getVieneDe();
            if (act.getMoneda() != MONEDA_INICIAL) {
                output[act.getMoneda()] += act.getN_monedas();
            }

        }

        //Retornamos la solucion
        return output;
    }

    @Override
    public String getTipo() {
        return "grafo backward";
    }
    
    //Adaptado directamente de la diapositiva 34
    public int backward(NodoBackward estoy, ArrayList<NodoBackward> visitados, Grafo<NodoBackward, Integer> mapa) {
        visitados.add(estoy);
        if (!calculado(estoy)) {
            ArrayList<NodoBackward> ady = mapa.adyacentes(estoy);
            if (ady.isEmpty()) {
                estoy.setCoste(0);
            } else {
                for (int k = 0; k < ady.size(); k++) {
                    NodoBackward voy = ady.get(k);
                    int costo = backward(voy, visitados, mapa);
                    if (mejor(costo + mapa.peso(estoy, voy), estoy.getCoste())) {
                        estoy.setCoste(costo + mapa.peso(estoy, voy));
                        estoy.setVieneDe(voy);
                    }
                }
            }
        }

        return estoy.getCoste();
    }

    private boolean mejor(int a, int b) {
        return a < b;
    }

    public boolean calculado(NodoBackward c) {
        return c.getCoste() < COSTE_INICIAL;
    }

    public Grafo<NodoBackward, Integer> generarMapa(NodoBackward primerNodo, int[] monedas, int cambio) {

        Grafo<NodoBackward, Integer> mapa = new Grafo<NodoBackward, Integer>(MAX_VERTICES, true);

        //Para ir guardando los nodos usamos una lista
        LinkedList<NodoBackward> anchura = new LinkedList<NodoBackward>();

        //Ponemos el primer nodo en la lista
        anchura.add(primerNodo);

        //Ponemos el primer nodo en el grafo
        mapa.nuevoVertice(primerNodo);

        while (!anchura.isEmpty()) {

            //Cogemos el primer nodo de la lista
            NodoBackward padre = anchura.poll();

            //Si queda por devolver mas de 0 y no se han acabado las monedas
            if (padre.getRestante() > 0 && padre.getMoneda() < monedas.length - 1) {

                //Creamos los nodos hijos
                for (int n_monedas = 0; n_monedas <= cambio; n_monedas++) {

                    //Asignamos la siguiente moneda
                    int moneda = padre.getMoneda() + 1;

                    //Salvo que sea la inicial que entonces es la 0
                    if (padre.getMoneda() == MONEDA_INICIAL) {
                        moneda = 0;
                    }

                    //Calculamos el cambio restante
                    int restante = padre.getRestante() - n_monedas * monedas[moneda];

                    //Si el restante es mayor o igual que cero el nodo es valido
                    if (restante >= 0) {

                        //Creamos el nodo y lo añadimos al grafo
                        NodoBackward hijo = new NodoBackward(moneda, restante, n_monedas);
                        anchura.add(hijo);
                        mapa.nuevoVertice(hijo);

                        //Creamos el arco correspondiente
                        int coste_arco = Math.abs(cambio - (hijo.getN_monedas() * monedas[hijo.getMoneda()]) - (hijo.getN_monedas()));
                        mapa.nuevoArco(padre, hijo, coste_arco);

                    }

                }

            }

        }
        return mapa;
    }

    private class NodoBackward {

        private int moneda;
        private int restante;
        private int n_monedas;
        private NodoBackward vieneDe;
        private int coste = COSTE_INICIAL;

        public NodoBackward(int moneda, int restante, int n_monedas) {
            this.moneda = moneda;
            this.restante = restante;
            this.n_monedas = n_monedas;
        }

        public NodoBackward getVieneDe() {
            return vieneDe;
        }

        public void setVieneDe(NodoBackward vieneDe) {
            this.vieneDe = vieneDe;
        }

        public int getMoneda() {
            return moneda;
        }

        public int getN_monedas() {
            return n_monedas;
        }

        public int getRestante() {
            return restante;
        }

        public int getCoste() {
            return coste;
        }

        public void setCoste(int coste) {
            this.coste = coste;
        }

    }
}
