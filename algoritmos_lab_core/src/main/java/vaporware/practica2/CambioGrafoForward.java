package vaporware.practica2;

import java.util.LinkedList;

public class CambioGrafoForward extends Cambio {
    protected static final int MONEDA_INICIAL = -999;

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {

        Nodo n = forward(monedas, cambio);

        int[] cantidades = new int[monedas.length];

        while (n.getPadre() != null) {

            if (n.getMoneda() != MONEDA_INICIAL) {
                cantidades[n.getMoneda()] += n.getCantidad();
            }

            n = n.getPadre();
        }

        return cantidades;
    }

    @Override
    public String getTipo() {
        return "grafo forward";
    }

    private Nodo forward(int[] monedas, int cambio) {
        //El primer nodo es del valor de la constante MONEDA_INICIAL
        Nodo primerNodo = new Nodo(MONEDA_INICIAL, 0, cambio, 0);

        //Para hacer el recorrido en anchura usamos una lista
        LinkedList<Nodo> anchura = new LinkedList<Nodo>();

        //Nodo solucion
        Nodo solucion = primerNodo;

        //Ponemos el primer nodo en la lista
        anchura.add(primerNodo);

        while (!anchura.isEmpty()) {

            //Cogemos el primer nodo de la lista
            Nodo padre = anchura.poll();

            //Si queda por devolver mas de 0 y no se han acabado las monedas
            if (padre.getRestante() > 0 && padre.getMoneda() < monedas.length - 1) {

                //Creamos los nodos hijos
                for (int n_monedas = 0; n_monedas <= cambio; n_monedas++) {

                    //La moneda sera la siguiente a la del padre
                    int moneda = padre.getMoneda() + 1;

                    //Salvo que sea la inicial que entonces es la 0
                    if (padre.getMoneda() == MONEDA_INICIAL) {
                        moneda = 0;
                    }

                    //Calculamos el cambio restante
                    int restante = padre.getRestante() - n_monedas * monedas[moneda];

                    //Si el restante es mayor o igual que cero el nodo es valido
                    if (restante >= 0) {

                        //El coste es el numero de monedas mas el coste del padre
                        int coste = n_monedas + padre.getCoste();

                        //Generamos un nodo nuevo
                        Nodo hijo = new Nodo(moneda, coste, restante, n_monedas);
                        hijo.setPadre(padre);

                        //Ponemos el nodo en la lista
                        if (!anchura.contains(hijo)) {
                            anchura.add(hijo);
                        }

                        //Si es mejor que la solucion actual lo marcamos como solucion
                        if (esMejor(hijo, solucion)) {
                            solucion = hijo;
                        }

                    } else {
                        //Si no es valido pasamos al siguiente en la lista
                        break;
                    }
                }
            }
        }
        return solucion;
    }

    private boolean esMejor(Nodo hijo, Nodo solucion) {
        /*
            Si ninguno tiene restante 0, el mejor es el de
            menos restante.

         */
        if (hijo.getRestante() != 0 && solucion.getRestante() != 0) {
            if (hijo.getRestante() < solucion.getRestante()) {
                return true;
            }
        }

        /*
            Si los dos tienen restante 0, el mejor es el que menos
            restante tenga.
         */
        if (hijo.getRestante() == 0 && solucion.getRestante() == 0) {
            if (hijo.getCoste() < solucion.getCoste()) {
                return true;
            }
        } else {
            /*
                Si uno tiene restante 0, ese es el mejor.
             */
            if (hijo.getRestante() == 0) {
                return true;
            }
        }

        return false;

    }

    private class Nodo {

        //Valor de la moneda que ha generado el nodo
        private int moneda;

        //Coste del nodo
        private int coste;

        //Cambio restante
        private int restante;

        //Cantidad de monedas usadas en este nodo
        private int cantidad;

        private Nodo padre = null;

        public Nodo(int moneda, int coste, int restante, int cantidad) {
            this.moneda = moneda;
            this.coste = coste;
            this.restante = restante;
            this.cantidad = cantidad;
        }

        public Nodo getPadre() {
            return padre;
        }

        public void setPadre(Nodo padre) {
            this.padre = padre;
        }

        public int getMoneda() {
            return moneda;
        }

        public int getCoste() {
            return coste;
        }

        public int getRestante() {
            return restante;
        }

        public int getCantidad() {
            return cantidad;
        }

    }

}
