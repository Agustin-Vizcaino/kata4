package agustin.kata3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class JFCPopulationBarChart extends JPanel implements BasicBarChart {

    @Override
    public void create(Map data) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Largest cities on Earth compared by population",
                "City name",
                "Population",
                makeDataset(data),
                PlotOrientation.VERTICAL,
                false,false,false);
        add(new ChartPanel(barChart));
    }

    public static CategoryDataset makeDataset(Map<Object,City> data) {
        try {
            DefaultCategoryDataset returner = new DefaultCategoryDataset();
            for (Object i : data.keySet()) {
                String key = i.toString();
                //data.get(key) instead of 100
                returner.addValue((double) data.get(key).getPopulation(), key, "Population");
            }
            return returner;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
