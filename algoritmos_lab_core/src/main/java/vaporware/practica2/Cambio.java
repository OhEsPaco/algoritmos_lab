package vaporware.practica2;

import java.math.BigInteger;

public abstract class Cambio {
    protected static final int MONEDA_INICIAL = -999;

    //Metodo llamado desde fuera para calcular el cambio
    public abstract int[] calcularCambio(int[] monedas, int cambio);

    //Metodo que devuelve el tipo de algoritmo
    public abstract String getTipo();
}
