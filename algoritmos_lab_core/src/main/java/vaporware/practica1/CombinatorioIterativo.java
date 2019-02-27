package vaporware.practica1;

import java.math.BigInteger;

public class CombinatorioIterativo extends Combinatorio {
    @Override
    protected BigInteger algoritmo(int n, int k) {
        //C(n,k)=n!/(k!*(n-k)!)
        return factorialIterativo(n).divide(factorialIterativo(k).multiply(factorialIterativo(n - k)));
    }

    @Override
    public String getTipo() {
        return "iterativo";
    }

    private BigInteger factorialIterativo(int n) {
        BigInteger fact = new BigInteger("1");

        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(new BigInteger(i + ""));
        }

        return fact;
    }

}
