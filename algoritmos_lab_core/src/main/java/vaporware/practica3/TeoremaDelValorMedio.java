package vaporware.practica3;

public class TeoremaDelValorMedio {
    private double radio;
    private double altura;

    public TeoremaDelValorMedio(double radio, double altura) {
        this.radio = radio;
        this.altura = altura;
    }

    public double[] algoritmo(int repeticiones) {
        //0-1 intervalo de confianza, 2 resultado
        double[] resultados = new double[3];
        double[] valores = new double[repeticiones];
        double suma = 0;
        double x, y, z;
        for (int i = 0; i < repeticiones; i++) {
            do {
                //Generamos un punto (x, y) entre 0 y el radio
                x = Math.random() * radio;
                y = Math.random() * radio;
                //Repetimos si el punto esta fuera de la base
            } while (!puntoValido(x, y, radio));
            //Calculamos la altura del cilindro correspondiente
            z = altura - (altura * Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / radio);

            //Calculamos el volumen del cilindro para esa altura
            suma = suma + Math.PI * Math.pow(radio, 2) * z;
            valores[i] = Math.PI *Math.pow(radio, 2) * z;

        }

        //Calculamos el intervalo de confianza
        resultados[0] = intervaloConfianza(valores)[0];
        resultados[1] = intervaloConfianza(valores)[1];

        //El resultado final es la media entre la suma y el numero de repeticiones
        resultados[2] = suma / repeticiones;
        return resultados;
    }

    //Comprueba si el punto esta dentro de la base
    private boolean puntoValido(double x, double y, double r) {
        //https://stackoverflow.com/questions/481144/equation-for-testing-if-a-point-is-inside-a-circle
        if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) {
            return true;
        }
        return false;
    }


    //Tomado de la diapositiva 16 del tema 3
    private double[] intervaloConfianza(double[] valores) {//n tamaño de la muestra
        double[] intervalo = new double[2];
        double media = media(valores);
        double S = cuasiV(valores, media);
        intervalo[0] = media - 1.96 * S / Math.sqrt(valores.length);
        intervalo[1] = media + 1.96 * S / Math.sqrt(valores.length);
        return intervalo;
    }

    //Tomado de la diapositiva 16 del tema 3
    private double media(double valores[]) {
        double media = 0;
        for (int x = 0; x < valores.length; x++) {
            media = media + valores[x];
        }
        return media / valores.length;
    }

    //Tomado de la diapositiva 16 del tema 3
    private double cuasiV(double[] valores, double media) {
        double S = 0;
        for (int x = 0; x < valores.length; x++) {
            S = S + Math.pow(valores[x] - media, 2);
        }
        return Math.sqrt(S / (valores.length - 1));
    }


}
