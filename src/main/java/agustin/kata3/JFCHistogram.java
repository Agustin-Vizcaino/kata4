package agustin.kata3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import java.util.Map;

public class JFCHistogram implements BasicHistogram {

    @Override
    public BasicHistogram create(Map data) {
        JFreeChart histogram = ChartFactory.createHistogram(
                "Largest cities on Earth compared by population",
                "City name",
                "Population",
                makeDataset(data),
                PlotOrientation.VERTICAL,
                true,false,false);
        return null;
    }

    public static HistogramDataset makeDataset(Map data) {
        HistogramDataset returner = new HistogramDataset();
        for (Object i : data.keySet()) {
            String key = i.toString();
            returner.addSeries(key,(double) data.get(key));
        }
        return null;
    }
}
