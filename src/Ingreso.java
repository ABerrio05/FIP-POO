public class Ingreso extends Finanzas {

    private double monto;
    private String fuente;
    private String frecuencia; //mensual, unico , semanal
    private String formaPago;

    public Ingreso(String detalles,String categoria,double monto,String fuente){
        super(detalles, categoria);
        this.monto=monto;
        this.fuente=fuente;
    }

    public String mostrarResumen() {
        return "Ingreso: " + getDetalles() + "\n" +
                "Categoría: " + getCategoria() + "\n" +
                "Monto: " + monto + "\n" +
                "Fuente: " + fuente + "\n" +
                "Forma de pago: " + formaPago + "\n" +
                "Frecuencia: " + frecuencia + "\n";
//                "Notas: " + notas;
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

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }


}
