package EdgeLength;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
public class EdgeLengthsMetricUI implements StatisticsUI {
    private MyMetricPanel panel;
    private EdgeLengthsMetric metric_class;

    @Override
    public JPanel getSettingsPanel() {
        return null;
        //panel = new MyMetricPanel();
        //return panel;
    }

    @Override
    public void setup(Statistics statistics) {
        this.metric_class = (EdgeLengthsMetric) statistics;
        if(panel!=null) {
            //panel.setDirected(myMetric.isDirected());
        }
    }

    @Override
    public void unsetup() {
        if(panel!=null) {
            //myMetric.setDirected(panel.isDirected());
        }
        panel = null;
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return EdgeLengthsMetric.class;
    }

    @Override
    public String getValue() {
        return metric_class.result;
    }

    @Override
    public String getDisplayName() {
        return "Edge length standard deviation";
    }

    @Override
    public String getShortDescription() {
        return "Calculate square root of the sample variance of the edge lengths.";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_EDGE_OVERVIEW;
    }

    @Override
    public int getPosition() {
        return 800;
    }

    private static class MyMetricPanel extends JPanel {
        public MyMetricPanel() {
        }
    }

}
