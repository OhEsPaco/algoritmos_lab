package vaporware.practica2;

import vaporware.Practica;

public class Practica2 implements Practica {
    @Override
    public void ejecutar() {
        int[] monedas = {3, 5};
        int cambio = 4;

        Cambio[] cambios = {new CambioGrafoForward()};

        for (Cambio c : cambios) {
            System.out.println("[Ejecutando " + c.getTipo() + "]");

            int res[] = c.calcularCambio(monedas, cambio);
            int total_devuelto=0;
            int n_monedas=0;
            for(int i=0;i<monedas.length;i++){
                System.out.println("<Moneda>"+monedas[i]+" <Cantidad>"+res[i]);
                n_monedas+=res[i];
                total_devuelto+=monedas[i]*res[i];
            }
            System.out.println("Total:" +n_monedas+" monedas.");
            System.out.println("Cantidad sin devolver:"+(cambio-total_devuelto));

        }


    }
}
