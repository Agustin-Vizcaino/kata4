package agustin.kata3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import javax.swing.*;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class JFCPopulationBarChart extends JPanel implements BasicBarChart {

    private int width = 700;
    private int height = 700;

    /*@Override
    public void create(Map data) {
        JFreeChart barChart = ChartFactory.createStackedBarChart(
                "Largest cities on Earth compared by population",
                "City name",
                "Population",
                makeDataset(data),
                PlotOrientation.HORIZONTAL,
                false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width, height));

        add(chartPanel);
    }*/

    @Override
    public void create(Map data) {
        JFreeChart barChart = ChartFactory.createStackedBarChart(
                "Largest cities on Earth compared by population",
                "City name",
                "Population",
                makeDataset(data),
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();

        // Rotate X-axis labels
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        // Customize X-axis label
        domainAxis.setLabel("City");

        // Customize bar renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        // Set custom item label position
        ItemLabelPosition position = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 2
        );
        renderer.setBasePositiveItemLabelPosition(position);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width, height));

        add(chartPanel);
    }

    public static CategoryDataset makeDataset(Map<Object, City> data) {
        DefaultCategoryDataset returner = new DefaultCategoryDataset();

        data.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue().getPopulation()))
                .limit(20)
                .forEach(entry -> returner.addValue(entry.getValue().getPopulation(), entry.getKey().toString(), entry.getValue().getName()));

        return returner;
    }
}
