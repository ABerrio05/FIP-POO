import java.util.*;

public class Usuario {

    private String nombre;
    private String apellido;
    private Integer edad;
    private Long documento;
    private String contraseña;
    private Double montoInicial;
    private String nombreUsuario;

    public Usuario(String nombre, String apellido, Integer edad, Long documento, String contraseña, String nombreUsuario, Double montoInicial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
        this.contraseña = contraseña;
        this.nombreUsuario = nombreUsuario;
        this.montoInicial = montoInicial;
    }

    public Usuario(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Double getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(Double montoInicial) {
        this.montoInicial = montoInicial;
    }

}
