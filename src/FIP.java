import java.util.*;

public class FIP {
    private static Scanner teclado = new Scanner(System.in);
    private static GestorUsuario gestorUsuario = new GestorUsuario();
    private static Map<Usuario, GestorFinanzas> gestoresFinanzas = new HashMap<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("""
                *** Bienvenido a FIP ***
                1. Ingresar usuario registrado
                2. Registrar nuevo usuario
                3. Salir
                """);

            int opcion = Integer.parseInt(teclado.nextLine().trim());

            switch (opcion) {
                case 1 -> iniciarSesion();
                case 2 -> registrarUsuario();
                case 3 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void iniciarSesion() {
        System.out.println("Ingrese usuario:");
        String usuario = teclado.nextLine().trim();
        System.out.println("Ingrese contraseña:");
        String contraseña = teclado.nextLine().trim();

        if (gestorUsuario.iniciarSesion(usuario, contraseña)) {
            Usuario usuarioActual = gestorUsuario.getUsuarioActual();
            GestorFinanzas gestorFinanzas = gestoresFinanzas.computeIfAbsent(
                    usuarioActual,
                    k -> new GestorFinanzas(usuarioActual)
            );
            menuFinanzas(gestorFinanzas);
        } else {
            System.out.println("❌ Usuario o contraseña incorrectos.");
        }
    }

    private static void registrarUsuario() {
        // ... (código existente de registro)
        System.out.println("Ingrese su nombre");
        String nombre = teclado.nextLine().toLowerCase().trim();
        System.out.println("Ingrese su apellido");
        String apellido = teclado.nextLine().toLowerCase().trim();
        System.out.println("Ingrese su edad");
        int edad = Integer.parseInt(teclado.nextLine().trim());
        System.out.println("Ingrese su numero de documento");
        long documento = Long.parseLong(teclado.nextLine().trim());
        System.out.println("Ingrese nombre de usuario");
        String nombreUsuario = teclado.nextLine().trim();
        System.out.println("Ingrese una contraseña");
        String contraseña = teclado.nextLine().trim();
        System.out.println("Ingrese la contraseña nuevamente para confirmar");
        String confirmarContraseña = teclado.nextLine().trim();
        if (!contraseña.equals(confirmarContraseña)) {
            System.out.println(" Las contraseñas no coinciden.");
        }
        System.out.println("Ingrese un monto inicial");
        Double montoInicial = Double.parseDouble(teclado.nextLine().trim());

    // Después de crear el usuario:
        Usuario nuevoUsuario = new Usuario(nombre, apellido, edad, documento, contraseña, nombreUsuario, montoInicial);
        if (gestorUsuario.agregarUsuario(nuevoUsuario)) {
            gestoresFinanzas.put(nuevoUsuario, new GestorFinanzas(nuevoUsuario));
            System.out.println("Usuario registrado exitosamente.");
        }
    }

    private static void menuFinanzas(GestorFinanzas gestor) {
        boolean volver = false;

        while (!volver) {
            System.out.println("""
                *** MENÚ FINANZAS ***
                1. Agregar ingreso
                2. Agregar gasto
                3. Agregar ahorro
                4. Generar reporte general
                5. Generar reporte por categoría
                6. Ver balance actual
                7. Volver
                """);

            int opcion = Integer.parseInt(teclado.nextLine().trim());

            switch (opcion) {
                case 1 -> agregarIngreso(gestor);
                case 2 -> agregarGasto(gestor);
                case 3 -> agregarAhorro(gestor);
                case 4 -> System.out.println(gestor.generarReporteGeneral());
                case 5 -> {
                    System.out.println("Ingrese categoría:");
                    String categoria = teclado.nextLine();
                    System.out.println(gestor.generarReportePorCategoria(categoria));
                }
                case 6 -> System.out.println("Balance actual: $" + gestor.calcularBalance());
                case 7 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void agregarIngreso(GestorFinanzas gestor) {
        System.out.println("Tipo de ingreso:\n1. Ahorro\n2. Categoría\n3. Cuenta");
        int tipo = Integer.parseInt(teclado.nextLine().trim());

        System.out.println("Detalles:");
        String detalles = teclado.nextLine();
        System.out.println("Categoría:");
        String categoria = teclado.nextLine();
        System.out.println("Monto:");
        double monto = Double.parseDouble(teclado.nextLine());
        System.out.println("Fuente:");
        String fuente = teclado.nextLine();

        Ingreso ingreso = new Ingreso(detalles, categoria, monto, fuente);
        gestor.agregarFinanza(ingreso);
        System.out.println("✅ Ingreso registrado.");
    }

    private static void agregarGasto(GestorFinanzas gestor) {
        // Similar a agregarIngreso pero para gastos
    }

    private static void agregarAhorro(GestorFinanzas gestor) {
        // Similar a agregarIngreso pero para ahorros
    }
}
