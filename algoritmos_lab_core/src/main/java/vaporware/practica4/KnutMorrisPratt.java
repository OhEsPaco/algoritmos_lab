package vaporware.practica4;

public class KnutMorrisPratt {
    public static int algoritmo(String patron, String texto, int[] fallo) {
        int pos = 0;
        int t = 0;
        int p = 0;
        while (texto.length() - t >= patron.length()) {
            if (p == patron.length()) {
                pos++;
                p = 0;
                t++;
            } else {
                if (texto.charAt(t + p) == patron.charAt(p)) p++;
                else {
                    t = t + p - fallo[p];
                    if (p > 0) p = fallo[p];
                }
            }
        }
        return pos;
    }

    public static int[] preproceso(String patron) {
        int[] fallo = new int[patron.length()];
        int i = 2;
        int j = 0;
        fallo[0] = -1;
        if (patron.length() > 1) {
            fallo[1] = 0;
            while (i < patron.length()) {
                if (patron.charAt(i - 1) == patron.charAt(j)) {
                    j++;
                    fallo[i] = j;
                    i++;
                } else {
                    if (j > 0) j = fallo[j];
                    else {
                        fallo[i] = 0;
                        i++;
                    }
                }
            }
        }
        return fallo;
    }
    public static int knuthMorrisPrat(String patron,String texto){
        int ocurrencias=0;
        if(patron.length()>0 && texto.length()>=patron.length()){
            int[] fallo=new int[patron.length()];
            fallo=preproceso(patron);//matriz de fallos
            ocurrencias=algoritmo(patron,texto,fallo);
        }
        return ocurrencias;
    }

}
