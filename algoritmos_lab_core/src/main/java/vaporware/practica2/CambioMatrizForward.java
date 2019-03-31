package vaporware.practica2;

import java.util.LinkedList;

public class CambioMatrizForward extends Cambio {

    @Override
    public int[] calcularCambio(int[] monedas, int cambio) {
        LinkedList<Integer>[][] matriz = new LinkedList[cambio + 1][monedas.length + 1];
        forward(matriz, monedas, cambio);
        int fil = 0;
        while (fil <= cambio && matriz[fil][monedas.length] == null) {
            fil++;
        }
        
        int[]output=new int[monedas.length];
        
        for(int i=0;i<monedas.length;i++){
            output[i]=matriz[fil][monedas.length].get(i);
        }
        return output;
    }

    @Override
    public String getTipo() {
        return "matriz forward";
    }

    public void forward(LinkedList<Integer>[][] matriz, int[] monedas, int cambio) {
        matriz[cambio][0] = new LinkedList<Integer>();
        for (int col = 0; col < monedas.length; col++) {
            for (int fil = cambio; fil >= 0; fil--) {
                if (matriz[fil][col] != null) {
                    for (int numMon = 0; fil - numMon * monedas[col] >= 0; numMon++) {
                        int nuevaFila = fil - numMon * monedas[col];
                        if (nuevaFila >= 0) {
                            LinkedList<Integer> nueva = (LinkedList<Integer>) matriz[fil][col].clone();
                            nueva.addLast(numMon);
                            if (matriz[nuevaFila][col + 1] == null || mejor(nueva, matriz[nuevaFila][col + 1])) {
                                matriz[nuevaFila][col + 1] = nueva;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean mejor(LinkedList<Integer> a, LinkedList<Integer> b) {
        return hayMonedas(a) < hayMonedas(b);
    }

    public int hayMonedas(LinkedList<Integer> a) {
        int h = 0;
        for (int n = 0; n < a.size(); n++) {
            h = h + a.get(n);
        }
        return h;
    }
}
