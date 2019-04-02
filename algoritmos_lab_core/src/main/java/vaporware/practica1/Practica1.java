package vaporware.practica1;

import vaporware.Practica;
import vaporware.utilidades.IO;
import vaporware.utilidades.ReporteEmpirico;

import java.math.BigInteger;

public class Practica1 implements Practica {
    @Override
    public void ejecutar() {
        System.out.println("-----Ejecutando practica 1-----");

        int n, k;
        Combinatorio[] experimentos = {new CombinatorioIterativo(), new CombinatorioRecursivo(), new CombinatorioPila()};
        ReporteEmpirico reporte = new ReporteEmpirico();

        //Pedimos n y k
        do {
            n = IO.pedirEntero("Introduce n:", "Eso no es un entero, prueba otra vez:");
            k = IO.pedirEntero("Introduce k:", "Eso no es un entero, prueba otra vez:");

            if (k > n) {
                System.out.println("n debe ser mayor o igual que k.");
            }
        } while (k > n);

        //Ejecucion de los algoritmos
        for (Combinatorio comb : experimentos) {
            System.out.println("[Ejecutando " + comb.getTipo() + "]");

            //Iniciamos el test
            reporte.runTest();
            try {
                //Ejecutamos el algoritmo
                BigInteger resultado = comb.calcularCombinatorio(n, k);

                //Paramos el test antes de imprimir la informacion
                reporte.runTest();

                //Imprimimos resultados
                System.out.println("El resultado es: " + resultado);
                System.out.println("Se han tardado " + reporte.toString() + "\n");

            } catch (java.lang.StackOverflowError | Exception a) {
                //Si hay una excepcion mostramos el tiempo que ha estado ejecutandose hasta el fallo
                reporte.runTest();
                System.out.println("El algoritmo " + comb.getTipo() + " ha fallado.");
                System.out.println("Se ha ejecutado durante " + reporte.toString() + " hasta el fallo\n");
            } finally {
                //Reset al reporte para las siguientes iteraciones
                reporte.reset();
            }


        }


    }

    @Override
    public String nombrePractica() {
        return "Calculo del numero combinatorio";
    }
}
