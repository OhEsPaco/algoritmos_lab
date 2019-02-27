package vaporware.practica1;

public abstract class Combinatorio {
    public Combinatorio(){

    }

    public long calcularCombinatorio(int n, int k){
        return algoritmo(n,k);
    }

    protected abstract long algoritmo(int n, int k);
    public abstract String getTipo();
}
