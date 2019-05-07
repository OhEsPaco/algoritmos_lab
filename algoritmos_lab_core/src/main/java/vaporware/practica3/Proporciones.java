package vaporware.practica3;

public class Proporciones {

    private double radio;
    private double altura;

    public Proporciones(double radio, double altura) {
        this.radio = radio;
        this.altura = altura;
    }

    public double[] volumenP(int repeticiones) {
        //Volumen probabilista lanzando tantos puntos como repeticiones
        //0-1 intervalo de confianza, 2 resultado
        double[] resultados = new double[3];
        int validos = 0;
        //Volumen del cilindro que contiene al cono
        double volCil = volumenCilindro();
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

            //Si el punto aleatorio cae dentro de z, es que el punto es bueno
            if (Math.random() * altura <= z) {
                validos++;
            }
        }

        //Calculamos el porcentaje de puntos que han sido validos
        double porcentajeValidos = validos / (double)repeticiones;

        //Calculamos el intervalo de confianza
        resultados[0] = IntervaloConfProporciones(porcentajeValidos, repeticiones)[0] * volCil;
        resultados[1] = IntervaloConfProporciones(porcentajeValidos, repeticiones)[1] * volCil;

        //El resultado final es el volumen de la figura que envuelve al cono * porcentaje de puntos validos
        resultados[2] = volCil * porcentajeValidos;

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

    //Calcula el volumen del cilindro
    private double volumenCilindro() {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    //Tomado de la diapositiva 17 del tema 3
    private double[] IntervaloConfProporciones(double p, int n) {
        double[] intervaloP = new double[2];
        intervaloP[0] = p - 1.96 * Math.sqrt(p * (1 - p) / n);
        intervaloP[1] = p + 1.96 * Math.sqrt(p * (1 - p) / n);
        return intervaloP;
    }

}
