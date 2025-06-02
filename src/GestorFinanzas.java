import java.util.*;
import java.util.stream.*;
import java.io.*;

public class GestorFinanzas {

    private List<Finanzas> finanzas;
    private Usuario usuario;
    private static final String ARCHIVO_FINANZAS = "Finanzas.txt";

    public GestorFinanzas(Usuario usuario) {
        this.finanzas = new ArrayList<>();
        this.usuario = usuario;
    }

    public void agregarFinanza(Finanzas finanza) {
        finanzas.add(finanza);
        guardarFinanzasEnArchivo();
    }
    public void agregarGasto(Finanzas finanza) {
        finanzas.add(finanza);
        guardarFinanzasEnArchivo();
    }
    public void agregarAhorro(Finanzas finanza) {
        finanzas.add(finanza);
        guardarFinanzasEnArchivo();
    }
    // Generar reporte general
    public String generarReporteGeneral() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE FINANZERO: \n");
        reporte.append("Usuario: ").append(usuario.getNombre()).append(" ").append(usuario.getApellido()).append("\n");
        reporte.append("Balance total: $").append(calcularBalance()).append("\n\n");

        reporte.append("Ingresos:\n");
        finanzas.stream().filter(f -> f.getTipo().equals("Ingreso")).forEach(f -> reporte.append("- ").append(f.getFechaHoraFormateada()).append(" | ").append(f.getDetalles()).append(": $").append(((Ingreso)f).getMonto()).append("\n"));

        reporte.append("\nGastos:\n");
        finanzas.stream().filter(f -> f.getTipo().equals("Gasto")).forEach(f -> reporte.append("- ").append(f.getFechaHoraFormateada()).append(" | ").append(f.getDetalles()).append(": $").append(((Gasto)f).getMonto()).append("\n"));

        reporte.append("\nAhorros:\n");
        finanzas.stream().filter(f -> f.getTipo().equals("Ahorro")).forEach(f -> reporte.append("- ").append(f.getFechaHoraFormateada()).append(" | ").append(f.getDetalles()).append(": $").append(((Ahorro)f).getMonto()).append("\n"));

        return reporte.toString();
    }

    // Calcular balance total
    public double calcularBalance() {
        double balance = usuario.getMontoInicial();

        balance += finanzas.stream().filter(f -> f.getTipo().equals("Ingreso")).mapToDouble(f -> ((Ingreso)f).getMonto()).sum();

        balance -= finanzas.stream().filter(f -> f.getTipo().equals("Gasto")).mapToDouble(f -> ((Gasto)f).getMonto()).sum();

        balance -= finanzas.stream().filter(f -> f.getTipo().equals("Ahorro")).mapToDouble(f -> ((Ahorro)f).getMonto()).sum();

        return balance;
    }

    // Reporte por categoría
    public String generarReportePorCategoria(String categoria) {
        List<Finanzas> filtradas = finanzas.stream().filter(f -> f.getCategoria().equalsIgnoreCase(categoria)).collect(Collectors.toList());

        if (filtradas.isEmpty()) {
            return "No hay transacciones en la categoría " + categoria;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE CATEGORÍA: ").append(categoria).append(" ===\n");

        filtradas.forEach(f -> {
            reporte.append(f.getTipo()).append(" - ").append(f.getDetalles()).append(": ");
            if (f instanceof Ingreso) {
                reporte.append("+$").append(((Ingreso)f).getMonto());
            } else if (f instanceof Gasto) {
                reporte.append("-$").append(((Gasto)f).getMonto());
            } else {
                reporte.append("Ahorro: $").append(((Ahorro)f).getMonto());
            }
            reporte.append("\n");
        });

        return reporte.toString();
    }

    public void modificarAhorroAgregar(Ahorro ahorro, double cambio) {
        double nuevoMonto = ahorro.getMonto() + cambio;
        ahorro.setMonto(nuevoMonto);
        System.out.println("Ahorro fue modificado exitosamente.");
        guardarFinanzasEnArchivo();
    }

    public void modificarAhorroRetirar(Ahorro ahorro, double cambio){
        if (cambio > ahorro.getMonto()) {
            System.out.println("El monto del ahorro no puede ser negativo");
        }else {
            double nuevoMonto = ahorro.getMonto() - cambio;
            ahorro.setMonto(nuevoMonto);
            System.out.println("Ahorro modificado exitosamente.");
            guardarFinanzasEnArchivo();
        }
    }

    public void eliminarAhorro(Ahorro ahorro) {
        finanzas.remove(ahorro);
        guardarFinanzasEnArchivo();
    }

    public void guardarFinanzasEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_FINANZAS))) {
            for (Finanzas f : finanzas) {
                writer.println(usuario.getNombreUsuario() + "," + f.getTipo() + "," + f.getDetalles() + "," + f.getCategoria() + "," + (f instanceof Ingreso ? ((Ingreso) f).getMonto() : f instanceof Gasto ? ((Gasto) f).getMonto() : ((Ahorro) f).getMonto()));
            }
        } catch (IOException e) {
            System.err.println("Error al guardar finanzas: " + e.getMessage());
        }
    }

    public void cargarFinanzasDesdeArchivo() {
        File archivo = new File(ARCHIVO_FINANZAS);
        if (!archivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 5) continue;
                // Solo carga finanzas del usuario actual
                if (datos[0].equals(usuario.getNombreUsuario())) {
                    Finanzas finanza = null;
                    switch (datos[1]) {
                        case "Ingreso"-> finanza = new Ingreso(datos[2], datos[3], Double.parseDouble(datos[4]));
                        case "Gasto"-> finanza = new Gasto(datos[2], datos[3], Double.parseDouble(datos[4]));
                        case "Ahorro"-> finanza = new Ahorro(datos[2], datos[3], Double.parseDouble(datos[4]));
                        default-> {break;}
                    }
                    if (finanza != null){
                        finanzas.add(finanza);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar finanzas: " + e.getMessage());
        }
    }

    public List<Gasto> getGastos() {
        return finanzas.stream().filter(f -> f.getTipo().equalsIgnoreCase("Gasto")).map(f -> (Gasto) f).collect(Collectors.toList());
        // casteamos para devolver la clase específica Gasto
    }

    public List<Ingreso> getIngresos() {
        return finanzas.stream().filter(f -> f.getTipo().equalsIgnoreCase("Ingreso")).map(f -> (Ingreso) f).collect(Collectors.toList());
        // casteamos para devolver la clase específica Gasto
    }

    public List<Ahorro> getAhorros() {
        return finanzas.stream().filter(f -> f.getTipo().equalsIgnoreCase("Ahorro")).map(f -> (Ahorro) f).collect(Collectors.toList());
        // casteamos para devolver la clase específica Gasto
    }

}
