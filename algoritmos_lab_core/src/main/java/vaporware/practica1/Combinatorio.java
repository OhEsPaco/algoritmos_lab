package vaporware.practica1;

import java.math.BigInteger;

public abstract class Combinatorio {

    //Metodo llamado desde fuera para calcular el combinatorio
    public BigInteger calcularCombinatorio(int n, int k) {
        return algoritmo(n, k);
    }

    //Metodo que encapsula el algoritmo
    protected abstract BigInteger algoritmo(int n, int k);

    //Metodo que devuelve el tipo de algoritmo
    public abstract String getTipo();

}
