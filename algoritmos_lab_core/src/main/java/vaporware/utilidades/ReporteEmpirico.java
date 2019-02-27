package vaporware.utilidades;

public class ReporteEmpirico {
    private long start_time = 0;
    private long end_time = 0;
    private int mode = 0;

    public ReporteEmpirico() {

    }

    public void runTest() {
        switch (mode) {
            case 0:
                start_time = System.currentTimeMillis();
                mode++;
                break;
            case 1:
                end_time = System.currentTimeMillis();
                mode++;
                break;
            default:
                System.out.println(this.toString());

        }
    }

    public void reset() {
        mode = 0;
    }

    public String toString() {
        return (end_time - start_time) + " millisegundos";
    }

}
