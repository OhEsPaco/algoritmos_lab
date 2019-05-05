package vaporware;

import vaporware.practica1.Practica1;
import vaporware.practica2.Practica2;
import vaporware.practica3.Practica3;
import vaporware.practica4.Practica4;
import vaporware.utilidades.IO;

public class App {
    public static void main(String[] args) {
        Practica4 p=new Practica4();
        p.ejecutar();

       /* Practica[] practicas = {new Practica1(), new Practica2()};

        do {
            int opcion = generarMenu(practicas);

            if (practicas.length == opcion) {
                System.exit(0);
            } else if (opcion >= 0 && opcion < practicas.length) {
                practicas[opcion].ejecutar();
            } else {
                System.out.println("Opcion incorrecta");
            }

        } while (true);*/

    }

    private static int generarMenu(Practica[] practicas) {
        for (int i = 0; i < practicas.length; i++) {
            System.out.println(i + "-[" + practicas[i].nombrePractica() + "]");
        }
        System.out.println((practicas.length) + "-[Salir]");

        return IO.pedirEntero("Introduce un numero", "Eso no es un numero");
    }
}
