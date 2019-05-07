package vaporware.practica3;

import vaporware.Practica;
import vaporware.utilidades.IO;

public class Practica3 implements Practica {
    @Override
    public void ejecutar() {

        int repes= IO.pedirEntero("Introduce el numero de repeticiones:", "Numero incorrecto");
        if(repes<=0){
            System.out.println("Repeticiones demasiado bajas, subiendo a 100");
            repes=100;
        }
        double radio=IO.pedirDouble("Introduce el radio:", "Numero incorrecto");
        double altura=IO.pedirDouble("Introduce la altura:", "Numero incorrecto");

            Proporciones p=new Proporciones(radio,altura);
            double[] vm=p.volumenP(repes);
            System.out.println("   VolumenP...."+vm[2]+ " ["+vm[0]+" ; "+vm[1]+"}  Volumen Real="+p.volumenReal());
            ValorMedio v=new ValorMedio(radio,altura);
            vm=v.volumenVm(repes);
            System.out.println("   VolumenVM...."+vm[2]+ " ["+vm[0]+" ; "+vm[1]+"}  Volumen Real="+v.volumenReal());

    }

    @Override
    public String nombrePractica() {
        return "cono";
    }
}
