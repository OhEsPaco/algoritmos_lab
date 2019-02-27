package vaporware;

import vaporware.practica1.Practica1;

public class App
{
    public static void main( String[] args )
    {
        Practica [] practicas = {new Practica1()};
        for(Practica p :practicas){
            p.ejecutar();
        }
    }
}
