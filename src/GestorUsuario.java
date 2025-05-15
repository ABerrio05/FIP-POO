import java.util.*;

public class GestorUsuario {

    private List<Usuario>usuarios;
    private Usuario usuarioActual;

    public GestorUsuario (){
        this.usuarios = new ArrayList<>();
    }

    public boolean agregarUsuario(Usuario nuevoUsuario) {
        if (existeUsuario(nuevoUsuario.getNombreUsuario())) {
            return false; // Usuario ya existe
        }
        usuarios.add(nuevoUsuario);
        return true;
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
