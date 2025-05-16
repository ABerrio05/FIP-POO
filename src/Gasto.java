
public class Gasto extends Finanzas {

    private String metodoPago;
    private Double monto;

    public Gasto(String detalles, String categoria, Double monto, String metodoPago) {
        super(detalles, categoria);
        this.metodoPago = metodoPago;
        this.monto = monto;
    }
    public Gasto (){

    }

    @Override
    public String getTipo(){
        return "Gasto";
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
