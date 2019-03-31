package vaporware.utilidades;

import java.util.Scanner;

public class IO {

    public static int pedirEntero(String msg, String error) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);

        do {
            String numero = sc.next();
            if (Utilidades.esNumero(numero)) {
                try {
                    return Integer.parseInt(numero);
                } catch (java.lang.NumberFormatException r) {
                    System.out.println("El numero es demasiado grande. Prueba otra vez:");
                }
            } else {
                System.out.println(error);
            }
        } while (true);
    }

    public static int[] pedirEnteros(String msg, String error) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        int[] salida = null;
        boolean continuar = true;

        do {

            String split = sc.nextLine();
            String[]splited=split.split("\\s+");
            salida = new int[splited.length];

            try {
                for (int i = 0; i < splited.length; i++) {

                    salida[i] = Integer.parseInt(splited[i]);

                }
                continuar = false;
            } catch (Exception e) {
                 
                System.out.println(error);
            }

        } while (continuar);

        return Utilidades.ordenarArray(salida);
    }

}
