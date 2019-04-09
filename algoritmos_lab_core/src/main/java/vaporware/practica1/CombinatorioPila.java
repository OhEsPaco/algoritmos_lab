package vaporware.practica1;

import java.math.BigInteger;
import java.util.Stack;

public class CombinatorioPila extends Combinatorio {
    private static final int SI_ES_CASO_BASE = 1;
    private static final int NO_ES_CASO_BASE = 0;

    @Override
    protected BigInteger algoritmo(int n, int k) {

        BigInteger resultado = new BigInteger("1");

        Stack<Instantanea> pila = new Stack<Instantanea>();

        Instantanea situacionActual;

        //Empezamos el algoritmo con n y k
        pila.add(new Instantanea(n, k, NO_ES_CASO_BASE));

        while (!pila.isEmpty()) {

            situacionActual = pila.pop();

            switch (situacionActual.getEsCasoBase()) {
                case NO_ES_CASO_BASE:
                    if (situacionActual.getN() != situacionActual.getK() && situacionActual.getK() != 0) {
                        //Si no es el caso base ponemos la esCasoBase a 1 y la devolvemos a la pila
                        situacionActual.setEsCasoBase(SI_ES_CASO_BASE);
                        pila.push(situacionActual);

                        //C(n-1,k-1)
                        pila.push(new Instantanea(situacionActual.getN() - 1, situacionActual.getK() - 1, NO_ES_CASO_BASE));

                        //C(n-1,k)
                        pila.push(new Instantanea(situacionActual.getN() - 1, situacionActual.getK(), NO_ES_CASO_BASE));
                    }
                    break;
                case SI_ES_CASO_BASE:
                    //C(n,k)=C(n-1,k-1)+C(n-1,k)
                    situacionActual.setResultadoParcial(resultado.add(situacionActual.getResultadoParcial()));
                    resultado = situacionActual.getResultadoParcial();
                    break;
            }
        }

        return resultado;
    }

    @Override
    public String getTipo() {
        return "pila";
    }

    private class Instantanea {
        private int n;
        private int k;
        private int esCasoBase;
        private BigInteger resultadoParcial = new BigInteger("1");

        public Instantanea(int n, int k, int esCasoBase) {
            this.n = n;
            this.k = k;
            this.esCasoBase = esCasoBase;

        }

        public BigInteger getResultadoParcial() {
            return resultadoParcial;
        }

        public void setResultadoParcial(BigInteger resultadoParcial) {
            this.resultadoParcial = resultadoParcial;
        }

        public int getN() {
            return n;
        }

        public int getK() {
            return k;
        }

        public int getEsCasoBase() {
            return esCasoBase;
        }

        public void setEsCasoBase(int esCasoBase) {
            this.esCasoBase = esCasoBase;
        }
    }
}
