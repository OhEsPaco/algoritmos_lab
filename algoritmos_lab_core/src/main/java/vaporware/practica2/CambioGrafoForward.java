package vaporware.practica2;

import java.util.LinkedList;

public class CambioGrafoForward extends Cambio {
    protected static final int MONEDA_INICIAL = -999;

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {

        NodoForward n = forward(monedas, cambio);

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

    private NodoForward forward(int[] monedas, int cambio) {
        //El primer nodo es del valor de la constante MONEDA_INICIAL
        NodoForward primerNodo = new NodoForward(MONEDA_INICIAL, 0, cambio, 0);

        //Para hacer el recorrido en anchura usamos una lista
        LinkedList<NodoForward> anchura = new LinkedList<NodoForward>();

        //NodoForward solucion
        NodoForward solucion = primerNodo;

        //Ponemos el primer nodo en la lista
        anchura.add(primerNodo);

        while (!anchura.isEmpty()) {

            //Cogemos el primer nodo de la lista
            NodoForward padre = anchura.poll();

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
                        NodoForward hijo = new NodoForward(moneda, coste, restante, n_monedas);
                        hijo.setPadre(padre);

                        //¿Hay un nodo con la misma moneda y restante?
                        if (anchura.contains(hijo)) {
                            //Si lo contiene, lo cogemos
                            NodoForward anterior = anchura.get(anchura.indexOf(hijo));

                            //¿Es mejor solucion el nuevo nodo?
                            if (hijo.getCoste() < anterior.getCoste()) {
                                anterior.setPadre(hijo.getPadre());
                                anterior.setCantidad(hijo.getCantidad());
                                anterior.setCoste(hijo.getCoste());
                                anterior.setMoneda(hijo.getMoneda());
                            }

                        } else {
                            //Ponemos el nodo en la lista
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

    private boolean esMejor(NodoForward hijo, NodoForward solucion) {
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

    private class NodoForward {

        //Valor de la moneda que ha generado el nodo
        private int moneda;

        //Coste del nodo (monedas gastadas a lo largo de la solucion)
        private int coste;

        //Cambio restante
        private int restante;

        //Cantidad de monedas usadas en este nodo
        private int cantidad;

        private NodoForward padre = null;

        public NodoForward(int moneda, int coste, int restante, int cantidad) {
            this.moneda = moneda;
            this.coste = coste;
            this.restante = restante;
            this.cantidad = cantidad;
        }

        public void setCoste(int coste) {
            this.coste = coste;
        }

        public NodoForward getPadre() {
            return padre;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public void setPadre(NodoForward padre) {
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

        @Override
        public boolean equals(Object o) {
            //Para el uso que le voy a dar me basta con saber si el restante es el mismo
            if (o == this) {
                return true;
            }

            if (!(o instanceof NodoForward)) {
                return false;
            }

            NodoForward c = (NodoForward) o;

            if (this.restante == c.getRestante()) {
                return true;
            }

            return false;
        }

        public void setMoneda(int moneda) {
            this.moneda = moneda;
        }
    }

}


