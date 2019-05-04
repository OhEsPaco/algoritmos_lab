package vaporware.practica3;

import vaporware.Practica;

public class Practica3 implements Practica {
    @Override
    public void ejecutar() {
        ValorMedio v=new ValorMedio();
       // double[] vm=v.volumenVm(200);
      //  System.out.println("   VolumenVM...."+vm[2]+ " ["+vm[0]+" ; "+vm[1]+"}  Volumen Real="+v.volumenReal());
        System.out.println(v.funcion(3,3));
    }

    @Override
    public String nombrePractica() {
        return "cono";
    }
}
