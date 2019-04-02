package vaporware.practica2;

import vaporware.Practica;
import vaporware.utilidades.IO;
public class Practica2 implements Practica {
    @Override
    public void ejecutar() {
        System.out.println("-----Ejecutando practica 2-----");
        
       // int[] monedas = IO.pedirEnteros("Introduce las monedas separadas por espacios:", "La entrada es erronea, prueba otra vez.");
      //  int cambio = IO.pedirEntero("Introduce el cambio", "Eso no es un entero, prueba otra vez:");
        int []monedas={1,2,5};
        int cambio=15;
        //Cambio[] cambios = {new CambioGrafoForward(), new CambioGrafoBackward(),new CambioMatrizForward(), new CambioMatrizBackward()};
        Cambio[] cambios = {new CambioMatrizBackward()};

        for (Cambio c : cambios) {
            
            System.out.println("[Ejecutando " + c.getTipo() + "]");
            int res[] = c.calcularCambio(monedas, cambio);
            int total_devuelto=0;
            int n_monedas=0;
            for(int i=0;i<monedas.length;i++){
                System.out.println("[Valor de la moneda]"+monedas[i]+" [Cantidad]"+res[i]);
                n_monedas+=res[i];
                total_devuelto+=monedas[i]*res[i];
            }
            System.out.println("[Numero de monedas]" +n_monedas);
            System.out.println("[Cantidad sin devolver]"+(cambio-total_devuelto)+"\n");

        }


    }
}
