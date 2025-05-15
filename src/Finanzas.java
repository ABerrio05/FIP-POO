import java.time.*;

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

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
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
