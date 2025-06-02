import java.util.*;
import java.io.*;

public class GestorUsuario {

    private List<Usuario>usuarios;
    private Usuario usuarioActual;
    private static final String ARCHIVO_USUARIOS = "Usuarios.txt";

    public GestorUsuario (){
        this.usuarios = new ArrayList<>();
    }

    public boolean agregarUsuario(Usuario nuevoUsuario) {
        if (existeUsuario(nuevoUsuario.getNombreUsuario())) {
            return false; // Usuario ya existe
        }
        usuarios.add(nuevoUsuario);
        guardarUsuariosArchivo();
        return true;
    }

    private void guardarUsuariosArchivo(){
        try(PrintWriter writer = new PrintWriter (new FileWriter (ARCHIVO_USUARIOS))){
            for (Usuario usuario : usuarios) {
                writer.println(usuario.getNombre() + "," + usuario.getApellido() + "," + usuario.getEdad() + "," + usuario.getDocumento() + "," + usuario.getContraseña() + "," + usuario.getNombreUsuario() + "," + usuario.getMontoInicial());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public void cargarUsuariosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Usuario usuario = new Usuario(datos[0], datos[1], Integer.parseInt(datos[2]), Long.parseLong(datos[3]), datos[4], datos[5], Double.parseDouble(datos[6]));
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                usuarioActual = usuario;
                return true;
            }
        }
        return false; // Credenciales incorrectas
    }

    private boolean existeUsuario(String nombreUsuario) {
        return usuarios.stream().anyMatch(u -> u.getNombreUsuario().equals(nombreUsuario));
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

}
