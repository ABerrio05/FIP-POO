import java.time.*;
import java.time.format.DateTimeFormatter;

public abstract class Finanzas {

    private String detalles;
    private LocalDateTime fechaHora;
    private String categoria;

    public Finanzas(String detalles, String categoria) {
        this.detalles = detalles;
        this.categoria = categoria;
        this.fechaHora = LocalDateTime.now();
    }

    public Finanzas(){

    }

    public abstract String getTipo();

    //Formatear la fecha para mostrarla de mejor manera
    private static final DateTimeFormatter formatearFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getFechaHoraFormateada() {
        return fechaHora.format(formatearFechaHora);
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
