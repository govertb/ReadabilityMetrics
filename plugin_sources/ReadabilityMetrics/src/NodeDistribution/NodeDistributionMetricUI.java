package NodeDistribution;

import EdgeLength.*;
import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
public class NodeDistributionMetricUI implements StatisticsUI {
    private MyMetricPanel panel;
    private NodeDistributionMetric metric_class;

    @Override
    public JPanel getSettingsPanel() {
        return null;
        //panel = new MyMetricPanel();
        //return panel;
    }

    @Override
    public void setup(Statistics statistics) {
        this.metric_class = (NodeDistributionMetric) statistics;
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
        return NodeDistributionMetric.class;
    }

    @Override
    public String getValue() {
        return metric_class.result;
    }

    @Override
    public String getDisplayName() {
        return "Node distribution uniformity";
    }

    @Override
    public String getShortDescription() {
        return "Calculates the std.dev. of all the Euclidean node pair distances.";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_NODE_OVERVIEW;
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
