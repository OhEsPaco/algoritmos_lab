package vaporware.utilidades;

import java.util.Scanner;

public class IO {

    public static int pedirEntero(String msg, String error) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        String numero = sc.next();
        if (Utilidades.esNumero(numero)) {
            return Integer.parseInt(numero);
        } else {
            do {
                System.out.println(error);
                numero = sc.next();
            } while (!Utilidades.esNumero(numero));
        }
        return Integer.parseInt(numero);
    }


}
