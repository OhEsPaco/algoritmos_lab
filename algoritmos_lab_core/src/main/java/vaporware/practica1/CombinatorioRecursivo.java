package vaporware.practica1;

import java.math.BigInteger;

public class CombinatorioRecursivo extends Combinatorio {
    @Override
    protected BigInteger algoritmo(int n, int k) {
        //C(n,k)=C(n-1,k-1)+C(n-1,k)

        //Caso base
        if (n == k || k == 0) {
            return new BigInteger("1");
        }

        //Caso recursivo
        return algoritmo(n - 1, k - 1).add(algoritmo(n - 1, k));
    }

    @Override
    public String getTipo() {
        return "recursivo";
    }
}
