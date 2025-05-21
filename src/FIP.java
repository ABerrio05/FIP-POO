import java.util.*;

public class FIP {
    private static Scanner teclado = new Scanner(System.in);
    private static GestorUsuario gestorUsuario = new GestorUsuario();
    private static Map<Usuario, GestorFinanzas> gestoresFinanzas = new HashMap<>();

    public static void main(String[] args) {
        boolean salir = false;
        System.out.println("*** Bienvenido a FIP ***");
        while (!salir) {
            System.out.print("""
                1. Ingresar usuario
                2. Registrar nuevo usuario
                3. Salir
                Seleccione una opcion:
                """);

            int opcion = Normalizar.normalizarInt(teclado);

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
            GestorFinanzas gestorFinanzas = gestoresFinanzas.computeIfAbsent(usuarioActual,k -> new GestorFinanzas(usuarioActual));
            menuFinanzas(gestorFinanzas);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private static void registrarUsuario() {
        System.out.println("Ingrese su nombre");
        String nombre = teclado.nextLine().toLowerCase().trim();
        System.out.println("Ingrese su apellido");
        String apellido = teclado.nextLine().toLowerCase().trim();
        System.out.println("Ingrese su edad");
        int edad = Normalizar.normalizarInt(teclado);
        if (edad < 17 || edad > 100){
            System.out.println("Usted debe tener entre 17 o 100 años para poder utilizar la aplicacion.");
        }else {
            System.out.println("Ingrese su numero de documento");
            long documento = Normalizar.normalizarLong(teclado);
            System.out.println("Ingrese nombre de usuario");
            String nombreUsuario = teclado.nextLine().trim();
            System.out.println("Ingrese una contraseña");
            String contraseña = teclado.nextLine().trim();
            System.out.println("Ingrese la contraseña nuevamente para confirmar");
            String confirmarContraseña = teclado.nextLine().trim();
            if (!contraseña.equals(confirmarContraseña)) {
                System.out.println("Las contraseñas no coinciden.");
            }else{
                System.out.println("Ingrese un monto inicial");
                Double montoInicial = Normalizar.normalizarDouble(teclado);

                // Después de crear el usuario:
                Usuario nuevoUsuario = new Usuario(nombre, apellido, edad, documento, contraseña, nombreUsuario, montoInicial);
                if (gestorUsuario.agregarUsuario(nuevoUsuario)) {gestoresFinanzas.put(nuevoUsuario, new GestorFinanzas(nuevoUsuario));
                    System.out.println("Usuario registrado exitosamente.");
                }
            }
        }
    }

    private static void menuFinanzas(GestorFinanzas gestor) {
        boolean volver = false;

        while (!volver) {
            System.out.print("""
                *** LISTA DE OPCIONES FINANCIERAS ***
                1. Agregar ingreso.
                2. Agregar gasto.
                3. Agregar ahorro.
                4. Editar ahorro existente.
                5. Eliminar ahorro.
                6. Generar reporte general.
                7. Generar reporte por cada categoría.
                8. Conocer balance actual.
                9. Generar grafica de gastos por categoria.
                10. Generar grafica de ingresos por categoria.
                11. Generar grafica ahorros por categoria.
                12. Regresar.
                Seleccione una opción:
                """);

            int opcion = Normalizar.normalizarInt(teclado);

            switch (opcion) {
                case 1 -> agregarIngreso(gestor);
                case 2 -> agregarGasto(gestor);
                case 3 -> agregarAhorro(gestor);
                case 4 -> modificarAhorro(gestor);
                case 5 -> eliminarAhorro(gestor);
                case 6 -> System.out.println(gestor.generarReporteGeneral());
                case 7 -> {
                    System.out.println("Ingrese categoría:");
                    String categoria = teclado.nextLine();
                    System.out.println(gestor.generarReportePorCategoria(categoria));
                }
                case 8 -> System.out.println("Balance actual: " + gestor.calcularBalance());
                case 9 -> {
                    List<Gasto> gastos = gestor.getGastos(); // usa el método que tengas para obtener los gastos
                    if (!gastos.isEmpty()) {
                        GraficoGastos grafica = new GraficoGastos(gastos);
                        grafica.setVisible(true);
                    } else {
                        System.out.println("No hay gastos registrados para mostrar en la gráfica.");
                    }
                }
                case 10 -> {
                    List<Ingreso> ingresos = gestor.getIngresos(); // usa el método que tengas para obtener los gastos
                    if (!ingresos.isEmpty()) {
                        GraficoIngresos grafica = new GraficoIngresos(ingresos);
                        grafica.setVisible(true);
                    } else {
                        System.out.println("No hay ingresos registrados para mostrar en la gráfica.");
                    }
                }
                case 11 -> {
                    List<Ahorro> ahorros = gestor.getAhorros(); // usa el método que tengas para obtener los gastos
                    if (!ahorros.isEmpty()) {
                        GraficoAhorros grafica = new GraficoAhorros(ahorros);
                        grafica.setVisible(true);
                    } else {
                        System.out.println("No existe ahorros registrados para graficar.");
                    }
                }
                case 12 -> volver = true;
                default -> System.out.println("Opción no valida.");
            }
        }
    }

    private static void agregarIngreso(GestorFinanzas gestor) {
        System.out.println("Digite la cantidad de su ingreso:");
        double monto = Normalizar.normalizarDouble(teclado);
        System.out.println("Digite el detalle del ingreso:");
        String detalles = teclado.nextLine();
        System.out.println("Digite la categoría donde desea ubicar el ingreso:");
        String categoria = teclado.nextLine();

        Ingreso ingreso = new Ingreso(detalles, categoria, monto);
        gestor.agregarFinanza(ingreso);
        System.out.println("Ingreso registrado.");
    }

    private static void agregarGasto(GestorFinanzas gestor) {
        System.out.println("Digite la cantidad del gasto:");
        double monto = Normalizar.normalizarDouble(teclado);
        if (monto > gestor.calcularBalance()){
            System.out.println("Error, fondos insuficientes, su saldo actual es de: " + gestor.calcularBalance());
        }else {

            System.out.println("Digite el detalles del gasto:");
            String detalles = teclado.nextLine();
            System.out.println("Digite la categoría donde desea ubicar el gasto:");
            String categoria = teclado.nextLine();

            Gasto gasto = new Gasto(detalles,categoria,monto);
            gestor.agregarGasto(gasto);
            System.out.println("Gasto registrado.");
        }
    }

    private static void agregarAhorro(GestorFinanzas gestor) {
        System.out.println("Digite la cantidad del ahorro:");
        double monto = Normalizar.normalizarDouble(teclado);
        if (monto > gestor.calcularBalance()){
            System.out.println("Error, fondos insuficientes, su saldo actual es de: " + gestor.calcularBalance());
        }else {
            System.out.println("Digite el detalle del ahorro:");
            String detalles = teclado.nextLine();
            System.out.println("Digite la categoría donde desea ubicar el ahorro :");
            String categoria = teclado.nextLine();

            Ahorro ahorro = new Ahorro(detalles, categoria, monto);
            gestor.agregarAhorro(ahorro);
            System.out.println("Ahorro registrado.");
        }
    }

    private static void modificarAhorro (GestorFinanzas gestor){
        List<Ahorro> ahorros = gestor.getAhorros();

        if (ahorros.isEmpty()) {
            System.out.println("No hay ahorros registrados para modificar.");
            return;
        }

        // Mostrar lista de ahorros
        System.out.println("Ahorros registrados");
        for (int i = 0; i < ahorros.size(); i++) {
            Ahorro a = ahorros.get(i);
            System.out.printf("%d. %s - Categoría: %s - Monto: $%.2f\n", i+1, a.getDetalles(), a.getCategoria(), a.getMonto());
        }

        System.out.println("Seleccione el número del ahorro que desea modificar (0 para cancelar):");
        int seleccion = Normalizar.normalizarInt(teclado) - 1;

        if (seleccion == -1) {
            System.out.println("Operación cancelada.");
            return;
        }

        if (seleccion < 0 || seleccion >= ahorros.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Ahorro ahorroSeleccionado = ahorros.get(seleccion);

        System.out.println("""
                1. Agregar dinero al ahorro.
                2. Retirar dinero del ahorro.
                3. cancelar.
                """);
        int opcion = Normalizar.normalizarInt(teclado);

        switch (opcion){
            case 1 ->{
                System.out.println("Ingrese la nueva cantidad a agregar: ");
                double cambio = Normalizar.normalizarDouble(teclado);

                // Modificar el ahorro
                gestor.modificarAhorroAgregar(ahorroSeleccionado, cambio);
            }
            case 2 ->{
                System.out.println("Ingrese la nueva cantidad a retirar:");
                double cambio = Normalizar.normalizarDouble(teclado);

                // Modificar el ahorro
                gestor.modificarAhorroRetirar(ahorroSeleccionado, cambio);
            }
            case 3 -> System.out.println("Volviendo al menu");

            default -> System.out.println("Opcion invalida, volviendo al menu");
        }
    }

    private static void eliminarAhorro (GestorFinanzas gestor){
        List<Ahorro> ahorros = gestor.getAhorros();

        if (ahorros.isEmpty()) {
            System.out.println("No hay ahorros registrados para eliminar.");
            return;
        }

        // Mostrar lista de ahorros
        System.out.println("Ahorros registrados");
        for (int i = 0; i < ahorros.size(); i++) {
            Ahorro a = ahorros.get(i);
            System.out.printf("%d. %s - Categoría: %s - Monto: $%.2f\n", i+1, a.getDetalles(), a.getCategoria(), a.getMonto());
        }

        System.out.println("Seleccione el número del ahorro que desea eliminar (0 para cancelar):");
        int seleccion = Normalizar.normalizarInt(teclado) - 1;

        if (seleccion == -1) {
            System.out.println("Operación cancelada.");
            return;
        }else if (seleccion < 0 || seleccion >= ahorros.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Ahorro ahorroSeleccionado = ahorros.get(seleccion);

        // Eliminar el ahorro
        gestor.eliminarAhorro(ahorroSeleccionado);
        System.out.println("Ahorro eliminado exitosamente.");

    }

}
