package vaporware.practica3;

public class Proporciones {

    private double r;
    private double h;

    public Proporciones(double r, double h) {
        this.r = r;
        this.h = h;
    }

    public double[] volumenP(int repes){
        double[] resultados=new double[3];//0 intervaloconfianza[0] 1 intervaloconfianza[1] 2 resultado
        double v=0;
        int buenos=0;
        double x,y;
        for(int n=0;n<repes;n++){
            do{
                x=Math.random()*r;//valor aleatorio de x entre [linf, lsup]
                y=Math.random()*r;//valor aleatorio de x entre [linf, lsup]
            }while(x*x+y*y>r*r);
            double z=funcion(x,y);
            double aleatorio=Math.random()*h;
            if(aleatorio<=z) buenos++;
        }
        double vc=volumenCilindro();
        //System.out.println((double) buenos/repes+"  buenos/repes");
        v=vc*(double)buenos/repes;
        resultados[0]=IntervaloConfProporciones((double)buenos/repes,repes)[0]*vc;
        resultados[1]=IntervaloConfProporciones((double)buenos/repes,repes)[1]*vc;
        resultados[2]=v;
        return resultados;
    }
    public double[] IntervaloConfProporciones(double p,int n){
        double[] intervaloP=new double[2];
        intervaloP[0]=p-1.96*Math.sqrt(p*(1-p)/n);
        intervaloP[1]=p+1.96*Math.sqrt(p*(1-p)/n);
//        System.out.println("p="+p+" ["+intervaloP[0]+" , "+intervaloP[1]+"]");
        return intervaloP;
    }

    private double volumenCilindro(){
        return Math.PI*r*r*h;
    }

    public double funcion(double x,double y){
        return h-(h*Math.sqrt(x*x+y*y)/r);//cono
    }

    public double volumenReal(){
        return Math.PI*r*r*h/3;
    }
}
