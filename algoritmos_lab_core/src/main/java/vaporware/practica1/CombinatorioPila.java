package vaporware.practica1;

import java.math.BigInteger;
import java.util.Stack;

public class CombinatorioPila extends Combinatorio {
    @Override
    protected BigInteger algoritmo(int n, int k) {
        BigInteger resultado=new BigInteger("1");
        Stack<Instantanea> pila=new Stack<Instantanea>();
        Instantanea situacionActual;

        pila.add(new Instantanea(n,k,0));

        while (!pila.isEmpty()){
            situacionActual=pila.pop();
            switch (situacionActual.getEtapa()){
                case 0:
                    if(situacionActual.getN()!=situacionActual.getK() && situacionActual.getK()!=0){
                        situacionActual.setEtapa(1);
                        pila.push(situacionActual);

                        Instantanea situacionSiguiente = new Instantanea(situacionActual.getN()-1,situacionActual.getK()-1,0);
                        pila.push(situacionSiguiente);
                    }
                    break;
                case 1:
                    if(situacionActual.getN()!=situacionActual.getK() && situacionActual.getK()!=0){
                        situacionActual.setEtapa(2);
                        pila.push(situacionActual);

                        Instantanea situacionSiguiente = new Instantanea(situacionActual.getN()-1,situacionActual.getK(),0);
                        pila.push(situacionSiguiente);
                    }
                    break;
                case 2:
                    situacionActual.setResultadoParcial(resultado.add(situacionActual.getResultadoParcial()));;
                    resultado=situacionActual.getResultadoParcial();

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
        private int etapa;
        private BigInteger resultadoParcial=new BigInteger("1");

        public Instantanea(int n, int k, int etapa) {
            this.n = n;
            this.k = k;
            this.etapa = etapa;

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

        public int getEtapa() {
            return etapa;
        }

        public void setEtapa(int etapa) {
            this.etapa = etapa;
        }
    }
}
