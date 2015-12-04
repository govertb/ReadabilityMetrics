package ModularityMetric;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
public class ModularityMetricUI implements StatisticsUI {
    private MyMetricPanel panel;
    private ModularityMetric metric_class;

    @Override
    public JPanel getSettingsPanel() {
        //panel = new MyMetricPanel();
        //return panel;
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        this.metric_class = (ModularityMetric) statistics;
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
        return ModularityMetric.class;
    }

    @Override
    public String getValue() {
        return metric_class.result;
    }

    @Override
    public String getDisplayName() {
        return "Modularity metric";
    }

    @Override
    public String getShortDescription() {
        return "Metric for calculating the degree of separation of communities.";
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
