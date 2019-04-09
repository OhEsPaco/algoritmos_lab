package vaporware.practica2;


public abstract class Cambio {


    //Metodo llamado desde fuera para calcular el cambio
    public abstract int[] calcularCambio(int[] monedas, int cambio);

    //Metodo que devuelve el tipo de algoritmo
    public abstract String getTipo();

}
