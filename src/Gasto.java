
public class Gasto extends Finanzas {

    private String metodoPago;
    private Double cantidadPago;

    public Gasto(String detalles, String categoria, String metodoPago, Double cantidadPago) {
        super(detalles, categoria);
        this.metodoPago = metodoPago;
        this.cantidadPago = cantidadPago;
    }
    public Gasto (){

    }

    @Override
    public String getTipo(){
        return " gasto ";
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Double getCantidadPago() {
        return cantidadPago;
    }

    public void setCantidadPago(Double cantidadPago) {
        this.cantidadPago = cantidadPago;
    }

}
