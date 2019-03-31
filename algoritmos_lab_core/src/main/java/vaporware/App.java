package vaporware;

import vaporware.practica1.Practica1;
import vaporware.practica2.CambioGrafoForward;
import vaporware.practica2.Practica2;

public class App {
    public static void main(String[] args) {


       Practica[] practicas = {new Practica2()};
        for (Practica p : practicas) {
            p.ejecutar();
        }
    }
}
