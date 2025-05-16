import java.util.*;
import java.util.stream.*;

public class GestorFinanzas {

    private List<Finanzas> finanzas;
    private Usuario usuario;

    public GestorFinanzas(Usuario usuario) {
        this.finanzas = new ArrayList<>();
        this.usuario = usuario;
    }

    // Método para agregar cualquier tipo de finanza
    public void agregarFinanza(Finanzas finanza) {
        finanzas.add(finanza);
    }
    public void agregarGasto(Finanzas finanza) {
        finanzas.add(finanza);
    }
    public void agregarAhorro(Finanzas finanza) {
        finanzas.add(finanza);
    }
    // Generar reporte general
    public String generarReporteGeneral() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE FINANZERO ===\n");
        reporte.append("Usuario: ").append(usuario.getNombre()).append(" ").append(usuario.getApellido()).append("\n");
        reporte.append("Balance total: $").append(calcularBalance()).append("\n\n");

        reporte.append("Ingresos:\n");
        finanzas.stream().filter(f -> f.getTipo().equals("Ingreso")).forEach(f -> reporte.append("- ").append(f.getDetalles()).append(": $").append(((Ingreso)f).getMonto()).append("\n"));

        reporte.append("\nGastos:\n");
        finanzas.stream().filter(f -> f.getTipo().equals("Gasto")).forEach(f -> reporte.append("- ").append(f.getDetalles()).append(": $").append(((Gasto)f).getMonto()).append("\n"));

        reporte.append("\nAhorros:\n");
        finanzas.stream().filter(f -> f.getTipo().equals("Ahorro")).forEach(f -> reporte.append("- ").append(f.getDetalles()).append(": $").append(((Ahorro)f).getMontoActual()).append("/$").append(((Ahorro)f).getMeta()).append("\n"));

        return reporte.toString();
    }

    // Calcular balance total
    public double calcularBalance() {
        double balance = usuario.getMontoInicial();

        balance += finanzas.stream()
                .filter(f -> f.getTipo().equals("Ingreso"))
                .mapToDouble(f -> ((Ingreso)f).getMonto())
                .sum();

        balance -= finanzas.stream()
                .filter(f -> f.getTipo().equals("Gasto"))
                .mapToDouble(f -> ((Gasto)f).getMonto())
                .sum();

        return balance;
    }

    // Reporte por categoría
    public String generarReportePorCategoria(String categoria) {
        List<Finanzas> filtradas = finanzas.stream()
                .filter(f -> f.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());

        if (filtradas.isEmpty()) {
            return "No hay transacciones en la categoría " + categoria;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE CATEGORÍA: ").append(categoria).append(" ===\n");

        filtradas.forEach(f -> {
            reporte.append(f.getTipo()).append(" - ").append(f.getDetalles()).append(": ");
            if (f instanceof Ingreso) {
                reporte.append("+$").append(((Ingreso)f).getMonto());
            } else if (f instanceof Gasto) {
                reporte.append("-$").append(((Gasto)f).getMonto());
            } else {
                reporte.append("Ahorro: $").append(((Ahorro)f).getMontoActual());
            }
            reporte.append("\n");
        });

        return reporte.toString();
    }

    // Métodos adicionales
    public List<Finanzas> getFinanzasPorTipo(String tipo) {
        return finanzas.stream()
                .filter(f -> f.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    public double getTotalAhorros() {
        return finanzas.stream()
                .filter(f -> f.getTipo().equals("Ahorro"))
                .mapToDouble(f -> ((Ahorro)f).getMontoActual())
                .sum();
    }
}
