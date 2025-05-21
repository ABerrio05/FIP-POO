import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraficoGastos extends JFrame {

    public GraficoGastos(List<Gasto> listaGastos) {
        setTitle("Gastos por Categoría");

        // Crear dataset para la gráfica
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agrupar gastos por categoría y sumar los montos
        Map<String, Double> sumaPorCategoria = new HashMap<>();
        for (Gasto gasto : listaGastos) {
            sumaPorCategoria.merge(gasto.getCategoria(), gasto.getMonto(), Double::sum);
        }

        // Agregar los datos al dataset
        for (Map.Entry<String, Double> entry : sumaPorCategoria.entrySet()) {
            dataset.addValue(entry.getValue(), "Gasto", entry.getKey());
        }

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "Gastos por Categoría", // Título
                "Categoría",            // Eje X
                "Monto",                // Eje Y
                dataset                 // Datos
        );

        // Mostrar el gráfico en un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cerrar esta ventana
    }
}


