package vaporware.utilidades;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Utilidades {

    public static boolean esNumero(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static int[] ordenarArray(int[] v) {
        Integer[] boxedArray = Arrays.stream(v).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArray);
        int[] primitive = new int[boxedArray.length];
        for(int i=0;i<primitive.length;i++){
            primitive[i]=boxedArray[i].intValue();
        }

        return primitive;
    }

    public static int[] ordenarArrayDescendente(int[] v) {
        Integer[] boxedArray = Arrays.stream(v).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArray, Collections.reverseOrder());
        int[] primitive = new int[boxedArray.length];
        for(int i=0;i<primitive.length;i++){
            primitive[i]=boxedArray[i].intValue();
        }

        return primitive;
    }

    public static void printM(LinkedList<Integer>[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.format("%15s", matriz[i][j]);
            }
            System.out.println();
        }

    }


    public static int numeroAleatorio(int Min, int Max){
       return Min + (int)(Math.random() * ((Max - Min) + 1));
    }
}
