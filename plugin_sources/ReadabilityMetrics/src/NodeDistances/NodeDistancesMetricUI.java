package NodeDistances;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
public class NodeDistancesMetricUI implements StatisticsUI {
    private MyMetricPanel panel;
    private NodeDistancesMetric metric_class;

    @Override
    public JPanel getSettingsPanel() {
        //panel = new MyMetricPanel();
        //return panel;
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        this.metric_class = (NodeDistancesMetric) statistics;
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
        return NodeDistancesMetric.class;
    }

    @Override
    public String getValue() {
        return metric_class.result;
    }

    @Override
    public String getDisplayName() {
        return "Node distances";
    }

    @Override
    public String getShortDescription() {
        return "Calculate the average distance between all nodes, neighbors and not neighborin nodes.";
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
