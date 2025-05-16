public class Ingreso extends Finanzas {

    private double monto;
//    private String fuente;
//    private String frecuencia; mensual, unico , semanal
//    private String metodoPago;

    public Ingreso(String detalles,String categoria,double monto){
        super(detalles, categoria);
        this.monto=monto;
    }

//    public String mostrarResumen() {
//        return "Ingreso: " + getDetalles() + "\n" +
//                "Categoría: " + getCategoria() + "\n" +
//                "Monto: " + monto + "\n" +
//                "Fuente: " + fuente + "\n" +
//                "Forma de pago: " + metodoPago + "\n" +
//                "Frecuencia: " + frecuencia + "\n";
//                "Notas: " + notas;
//    }

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

//    public String getFuente() {
//        return fuente;
//    }
//
//    public void setFuente(String fuente) {
//        this.fuente = fuente;
//    }

//    public String getFrecuencia() {
//        return frecuencia;
//    }


//    public void setFrecuencia(String frecuencia) {
//        this.frecuencia = frecuencia;
//    }


}
