package vaporware.practica1;

import vaporware.Practica;
import vaporware.utilidades.IO;
import vaporware.utilidades.ReporteEmpirico;

import java.math.BigInteger;

public class Practica1 implements Practica {
    @Override
    public void ejecutar() {
        System.out.println("-----Ejecutando practica 1-----");
        int n,k;
        Combinatorio[] experimentos = {new CombinatorioIterativo(), new CombinatorioRecursivo(), new CombinatorioPila()};
        ReporteEmpirico reporte=new ReporteEmpirico();
        n= IO.pedirEntero("Introduce n:","Eso no es un entero, prueba otra vez:");
        k= IO.pedirEntero("Introduce k:","Eso no es un entero, prueba otra vez:");


        for (Combinatorio comb:experimentos){
            System.out.println("[Ejecutando "+comb.getTipo()+"]");
            reporte.runTest();
            BigInteger resultado=comb.algoritmo(n,k);
            reporte.runTest();
            System.out.println("El resultado es: "+resultado);
            System.out.println("Se han tardado "+ reporte.toString()+"\n");
            reporte.reset();

        }



    }
}
