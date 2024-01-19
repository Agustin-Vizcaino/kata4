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

    @Override
    public void create(Map data) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Largest cities on Earth compared by population",
                "City name",
                "Population",
                makeDataset(data),
                PlotOrientation.HORIZONTAL,
                false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(700, 700)); // Set the initial preferred size

        // Set the chart panel to expand and fill the space
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);

        add(chartPanel);
    }

    /**@Override
    public void create(Map data) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Largest cities on Earth compared by population",
                "City name",
                "Population",
                makeDataset(data),
                PlotOrientation.VERTICAL,
                true, false, false);

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

        add(new ChartPanel(barChart));
    }*/

    public static CategoryDataset makeDataset(Map<Object, City> data) {
        try {
            DefaultCategoryDataset returner = new DefaultCategoryDataset();

            // Sort the entries by population in descending order
            data.entrySet().stream()
                    .sorted(Map.Entry.<Object, City>comparingByValue(Comparator.comparingInt(City::getPopulation)).reversed())
                    .limit(10)
                    .forEach(entry -> {
                        String key = entry.getKey().toString();
                        //Depending on whether you want to show the city names, which doesn't work
                        returner.addValue((double) entry.getValue().getPopulation(), key, entry.getValue().getName());
                        //returner.addValue((double) entry.getValue().getPopulation(), key, entry.getValue().getName());
                    });

            return returner;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
