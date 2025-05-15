public class Ahorro extends Finanzas {

    private double montoActual;
    private double meta;

    public Ahorro(String detalles, String categoria, double montoActual, double meta) {
        super(detalles, categoria);
        this.meta = meta;
        this.montoActual = montoActual;
    }

    @Override
    public String getTipo() {
        return "Ahorro";
    }
    public double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(double montoActual) {
        this.montoActual = montoActual;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    //Metodo para cumprir la meta
    public boolean seCumplio() {
        return montoActual >= meta;
    }

    //Metodo avisos
    public void verificar() {
        if (seCumplio()) {
            System.out.println("¡Felicidades! Has alcanzado tu meta de ahorro.");

        } else {
            System.out.println("Sigue ahorrando, aún no llegas a la meta");
        }
    }

}
