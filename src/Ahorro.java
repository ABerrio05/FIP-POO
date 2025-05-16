public class Ahorro extends Finanzas {

    private double monto;
//    private double meta;

    public Ahorro(String detalles, String categoria, double monto) {
        super(detalles, categoria);
        this.monto = monto;
    }

    @Override
    public String getTipo() {
        return "Ahorro";
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

//    public double getMeta() {
//        return meta;
//    }

//    public void setMeta(double meta) {
//        this.meta = meta;
//    }

    //Metodo para cumprir la meta
//    public boolean seCumplio() {
//        return montoActual >= meta;
//    }

    //Metodo avisos
//    public void verificar() {
//        if (seCumplio()) {
//            System.out.println("¡Felicidades! Has alcanzado tu meta de ahorro.");
//
//        } else {
//            System.out.println("Sigue ahorrando, aún no llegas a la meta");
//        }
//    }

}
