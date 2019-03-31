package vaporware.utilidades;

import java.util.Arrays;
import java.util.Collections;

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
        Arrays.sort(boxedArray, Collections.reverseOrder());
        int[] primitive = new int[boxedArray.length];
        for(int i=0;i<primitive.length;i++){
            primitive[i]=boxedArray[i].intValue();
        }
        
        return primitive;
    }
}
