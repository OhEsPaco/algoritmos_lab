package vaporware.practica1;

import java.math.BigInteger;

public abstract class Combinatorio {
    public Combinatorio(){

    }

    public BigInteger calcularCombinatorio(int n, int k){
        return algoritmo(n,k);
    }

    protected abstract BigInteger algoritmo(int n, int k);
    public abstract String getTipo();
}
