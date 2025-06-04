public class Gasto extends Finanzas {

    private Double monto;

    public Gasto(String detalles, String categoria, Double monto) {
        super(detalles, categoria);
        this.monto = monto;
    }
    public Gasto (){

    }

    @Override
    public String getTipo(){
        return "Gasto";
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
