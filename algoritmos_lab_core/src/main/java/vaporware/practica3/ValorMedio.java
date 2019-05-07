package vaporware.practica3;

public class ValorMedio {
    private double r;
    private double h;

    public ValorMedio(double r, double h) {
        this.r = r;
        this.h = h;
    }
    public double[] volumenVm(int repes) {
        double[] resultados = new double[3];//0 intervaloconfianza[0] 1 intervaloconfianza[1] 2 resultado
        double[] valores = new double[repes];
        double aux = 0.0;
        double x, y;
        for (int n = 0; n < repes; n++) {
            do {
                x = Math.random() * r;
                y = Math.random() * r;
            } while (x * x + y * y > r * r);
            double z = funcion(x, y);
            aux = aux + Math.PI * r * r * z;
            valores[n] = Math.PI * r * r * z;

        }

        resultados[0] = intervaloConfianza(valores)[0];
        resultados[1] = intervaloConfianza(valores)[1];
        resultados[2] = (double) aux / repes;
        return resultados;
    }

    public double[] intervaloConfianza(double[] valores) {//n tamaño de la muestra
        double[] intervalo = new double[2];
        double media = media(valores);

        double S = cuasiV(valores, media);
        intervalo[0] = media - 1.96 * S / Math.sqrt(valores.length);
        intervalo[1] = media + 1.96 * S / Math.sqrt(valores.length);
        return intervalo;
    }

    public double media(double valores[]) {
        double media = 0;
        for (int x = 0; x < valores.length; x++) {
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
