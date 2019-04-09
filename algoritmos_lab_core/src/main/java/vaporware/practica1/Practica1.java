package vaporware.practica1;

import vaporware.Practica;
import vaporware.utilidades.IO;

import java.math.BigInteger;

public class Practica1 implements Practica {

        /*RESULTADOS
    n=20202 k=202
    it=328ms
    pila=no termina
    rec=no termina

    n=2020 k=202
    it=16ms
    pila=no termina
    rec=no termina

    n=30, k=15
    it=0ms
    pila=22451ms
    rec=9499ms

    n=20, k=5
    it=0ms
    pila=16ms
    rec=0ms

    n=10, k=5
    it=0ms
    pila=0ms
    rec=0ms

    El metodo iterativo es muy rapido en cualquier situacion. El recursivo es mas
    rapido normalmete que el de pila, aunque se vuelven tiempos infinitos a la vez.
     */


    @Override
    public void ejecutar() {
        System.out.println("-----Ejecutando practica 1-----");

        int n, k;
        Combinatorio[] experimentos = {new CombinatorioIterativo(), new CombinatorioPila(), new CombinatorioRecursivo()};

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
            long end_time = 0;
            long start_time = System.currentTimeMillis();
            try {
                //Ejecutamos el algoritmo
                BigInteger resultado = comb.calcularCombinatorio(n, k);

                //Paramos el test antes de imprimir la informacion
                end_time = System.currentTimeMillis();

                //Imprimimos resultados
                System.out.println("El resultado es: " + resultado);
                System.out.println("Se han tardado " + (end_time - start_time) + " milisegundos\n");

            } catch (java.lang.StackOverflowError | Exception a) {
                //Si hay una excepcion mostramos el tiempo que ha estado ejecutandose hasta el fallo
                end_time = System.currentTimeMillis();
                System.out.println("El algoritmo " + comb.getTipo() + " ha fallado.");
                System.out.println("Se ha ejecutado " + (end_time - start_time) + " milisegundos hasta el fallo\n");
            }

        }


    }

    @Override
    public String nombrePractica() {
        return "Calculo del numero combinatorio";
    }
}
