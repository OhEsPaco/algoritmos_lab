package vaporware.practica3;

import vaporware.Practica;
import vaporware.utilidades.IO;

public class Practica3 implements Practica {
    @Override
    public void ejecutar() {
        //Pedimos el numero de repeticiones
        int n_repeticiones = IO.pedirEntero("Introduce el numero de repeticiones:", "Numero incorrecto");
        if (n_repeticiones <= 0) {
            System.out.println("Repeticiones demasiado bajas, subiendo a 100");
            n_repeticiones = 100;
        }

        //Pedimos los datos del cono
        double radio = IO.pedirDouble("Introduce el radio:", "Numero incorrecto");
        double altura = IO.pedirDouble("Introduce la altura:", "Numero incorrecto");
        double volumen = Math.PI * Math.pow(radio, 2) * altura / 3;

        System.out.println("Altura: "+altura+" Radio: "+radio+" Volumen: "+volumen);

        System.out.println("--Calculo por proporciones--");
        Proporciones p = new Proporciones(radio, altura);
        double[] resultados = p.volumenP(n_repeticiones);
        System.out.println("Intervalo de confianza ["+resultados[0]+"--"+resultados[1]+"] Volumen: "+resultados[2]);

        System.out.println("--Calculo por teorema del valor medio--");
        TeoremaDelValorMedio v = new TeoremaDelValorMedio(radio, altura);
        resultados = v.algoritmo(n_repeticiones);
        System.out.println("Intervalo de confianza ["+resultados[0]+"--"+resultados[1]+"] Volumen: "+resultados[2]+"\n");

    }

    @Override
    public String nombrePractica() {
        return "Volumen del cono";
    }


}
