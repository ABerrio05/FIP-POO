public class Ahorro extends Finanzas {

    private double monto;

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

}
