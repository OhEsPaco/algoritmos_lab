package vaporware.practica4;

import vaporware.Practica;

import java.io.IOException;

public class Practica4 implements Practica {
    @Override
    public void ejecutar() {


        try {
            System.out.println("Karp "+KarpRabin.ejecutar("quijote1.txt","el",25));
            System.out.println("Naive "+Naive.ejecutar("quijote1.txt","el"));
            System.out.println("Knut "+KnutMorrisPratt.ejecutar("quijote1.txt","el"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String nombrePractica() {
        return "texto";
    }
}
