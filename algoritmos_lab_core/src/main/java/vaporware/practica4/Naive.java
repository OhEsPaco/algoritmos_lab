package vaporware.practica4;

public class Naive {
    public static int naive(String patron, String texto) {
        int ocurrencias = 0;
        if (patron.length() > 0 && texto.length() >= patron.length()) {
            int t = 0;//desplazamiento en el texto
            int p = 0;//desplazamiento en el patron
            while (texto.length() - t >= patron.length()) {
                if (texto.charAt(t) == patron.charAt(p)) {//busqueda
                    int T = t + 1;
                    int P = 1;
                    while (P < patron.length() && texto.charAt(T) == patron.charAt(P)) {
                        T++;
                        P++;
                    }
                    if (P == patron.length()) {
                        ocurrencias++;
                    }
                }
                t++;
            }
        }
        return ocurrencias;
    }

}
