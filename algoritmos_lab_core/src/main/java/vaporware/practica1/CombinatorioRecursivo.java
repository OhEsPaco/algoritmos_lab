package vaporware.practica1;

public class CombinatorioRecursivo extends Combinatorio {
    @Override
    protected long algoritmo(int n, int k) {
        //C(n,k)=C(n-1,k-1)+C(n-1,k)
        switch (k){
            case 0:
                return 1;
            case 1:
                return n;
            default:


        }
        return algoritmo(n-1,k-1)+algoritmo(n-1,k);
    }

    @Override
    public String getTipo() {
        return "recursivo";
    }
}
