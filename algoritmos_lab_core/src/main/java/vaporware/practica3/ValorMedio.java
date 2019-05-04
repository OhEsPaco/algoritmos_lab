package vaporware.practica3;

public class ValorMedio {
    double r=10;
    double h=5;

    public double[] volumenVm(int repes) {
        double[] resultados = new double[3];//0 intervaloconfianza[0] 1 intervaloconfianza[1] 2 resultado
        double[] valores = new double[repes];
        double aux = 0.0;
        double x, y;
        for (int n = 0; n < repes; n++) {
            do {
                x = Math.random() * r;//valor aleatorio de x entre [linf, lsup]
                y = Math.random() * r;//valor aleatorio de x entre [linf, lsup]
            } while (x * x + y * y > r * r);
            double z = funcion(x, y);
            aux = aux + Math.PI * r * r * z;//area de la base*altura Pi*1^2*z
            valores[n] = Math.PI * r * r * z;
          System.out.println(n+"  x..."+x+"   y..."+y+"  z.."+z+"  aux..."+aux);
        }
      //  System.out.println(aux+"  "+repes);
        resultados[0] = intervaloConfianza(valores)[0];
        resultados[1] = intervaloConfianza(valores)[1];
        resultados[2] = (double) aux / repes;
        return resultados;
    }

    public double[] intervaloConfianza(double[] valores) {//n tamaño de la muestra
        double[] intervalo = new double[2];
        double media = media(valores);
//System.out.println("media="+media);
        double S = cuasiV(valores, media);
        intervalo[0] = media - 1.96 * S / Math.sqrt(valores.length);
        intervalo[1] = media + 1.96 * S / Math.sqrt(valores.length);
        return intervalo;
    }

    public double media(double valores[]) {
        double media = 0;
        for (int x = 0; x < valores.length; x++) {
//System.out.println("media="+media+"  valores["+x+"]="+valores[x]);
            media = media + valores[x];
        }
        return media / valores.length;
    }

    public double cuasiV(double[] valores, double media) {
        double S = 0;
        for (int x = 0; x < valores.length; x++) {
            S = S + Math.pow(valores[x] - media, 2);
        }
        return Math.sqrt(S / (valores.length - 1));
    }

    public double funcion(double x, double y) {
        return h - (h * Math.sqrt(x * x + y * y) / r);//cono
    }
    public double volumenReal(){
        return Math.PI*r*r*h/3;
    }
}
