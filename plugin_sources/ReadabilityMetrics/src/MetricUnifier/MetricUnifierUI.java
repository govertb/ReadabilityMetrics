package MetricUnifier;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
public class MetricUnifierUI implements StatisticsUI {
    private MyMetricPanel panel;
    private MetricUnifier metric_class;

    @Override
    public JPanel getSettingsPanel() {
        return null; // hier nog eventueel gewenste metrics aanklikken
        //panel = new MyMetricPanel();
        //return panel;
    }

    @Override
    public void setup(Statistics statistics) {
        this.metric_class = (MetricUnifier) statistics;
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
        return MetricUnifier.class;
    }

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public String getDisplayName() {
        return "All readability metrics";
    }

    @Override
    public String getShortDescription() {
        return "Executes all readability metrics.";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_NETWORK_OVERVIEW;
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
