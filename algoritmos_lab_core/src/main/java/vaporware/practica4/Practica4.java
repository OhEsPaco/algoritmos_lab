package vaporware.practica4;

import vaporware.Practica;
import vaporware.utilidades.IO;
import vaporware.utilidades.Utilidades;

import java.io.IOException;
import java.util.ArrayList;

public class Practica4 implements Practica {
    private int nLineasTotal = 0;
    private int nLineasTomadas = 0;

    @Override
    public void ejecutar() {

        try {
            int ocurrencias = 0;

            String nombreTexto = IO.pedirString("Archivo a analizar:", "Esto no es un String");
            String patron = IO.pedirString("Patron a buscar:", "Esto no es un String");

            int porcentaje = IO.pedirEntero("Introduce el porcentaje de texto a analizar", "Eso no es un numero.\n");

            if (porcentaje < 0) {
                System.out.println("Porcentaje demasiado bajo. Subiendo a 25%");
                porcentaje = 25;
            } else if (porcentaje > 100) {
                System.out.println("Porcentaje demasiado alto. Bajando a 100%");
                porcentaje = 100;
            }

            String archivo = cargarArchivo(nombreTexto, porcentaje);
            //Comprobamos que el texto sea mayor o igual que el patron
            //y que el patron tenga mas de 0 caracteres
            if (patron.length() > 0 && archivo.length() >= patron.length()) {
                System.out.println("--Naive");
                long start_time = System.currentTimeMillis();
                ocurrencias = Naive.ejecutar(archivo, patron);
                long end_time = System.currentTimeMillis();
                estimar(ocurrencias);
                System.out.println("Se han tardado " + (end_time - start_time) + " milisegundos\n");

                System.out.println("--Karp Rabin");
                start_time = System.currentTimeMillis();
                ocurrencias = KarpRabin.ejecutar(archivo, patron);
                end_time = System.currentTimeMillis();
                estimar(ocurrencias);
                System.out.println("Se han tardado " + (end_time - start_time) + " milisegundos\n");

                System.out.println("--Knuth Morris Pratt");
                start_time = System.currentTimeMillis();
                ocurrencias = KnuthMorrisPratt.ejecutar(archivo, patron);
                end_time = System.currentTimeMillis();
                estimar(ocurrencias);
                System.out.println("Se han tardado " + (end_time - start_time) + " milisegundos\n");

            } else {
                System.out.println("Patron demasiado corto o texto demasiado corto\n");
            }


        } catch (IOException e) {
            System.out.println("Texto no encontrado");
        }
    }

    @Override
    public String nombrePractica() {
        return "Busqueda de patrones";
    }

    private void estimar(int ocurrencias) {
        System.out.println("Ha habido " + ocurrencias + " ocurrencias del patron.");
        System.out.println("Se han tomado " + nLineasTomadas + " lineas del total de " + nLineasTotal);
        int estimacion = Math.round((ocurrencias * nLineasTotal) / (nLineasTomadas * 1f));
        System.out.println("Se estima un total de " + estimacion + " ocurrencias.");

    }

    private String cargarArchivo(String archivo, int porcentaje) throws IOException {
        ArrayList<String> texto = IO.leerArchivo(archivo);
        this.nLineasTotal = texto.size();
        int nLineas = Math.round((porcentaje / 100f) * texto.size());
        this.nLineasTomadas = nLineas;
        int inicio_texto = Utilidades.numeroAleatorio(0, texto.size() - nLineas);
        int final_texto = inicio_texto + nLineas;
        String salida = "";
        for (int i = inicio_texto; i < final_texto; i++) {
            salida += texto.get(i);
        }
        return salida;

    }

}
