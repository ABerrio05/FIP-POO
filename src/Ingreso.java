public class Ingreso extends Finanzas {

    private double monto;

    public Ingreso(String detalles,String categoria,double monto){
        super(detalles, categoria);
        this.monto=monto;
    }

    @Override
    public String getTipo(){
        return "Ingreso";
    }


    public double getMonto() {
        return monto;
    }


    public void setMonto(double monto) {
        this.monto = monto;
    }


}
