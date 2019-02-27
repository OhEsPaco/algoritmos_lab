package vaporware.practica1;

public class CombinatorioIterativo extends Combinatorio {
    @Override
    protected long algoritmo(int n, int k) {
        //C(n,k)=n!/(k!*(n-k)!)
        return factorialIterativo(n)/(factorialIterativo(k)*factorialIterativo(n-k));

    }

    @Override
    public String getTipo() {
        return "iterativo";
    }

    private long factorialIterativo(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

}
