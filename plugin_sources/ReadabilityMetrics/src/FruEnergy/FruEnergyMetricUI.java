package FruEnergy;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
public class FruEnergyMetricUI implements StatisticsUI {
    private MyMetricPanel panel;
    private FruEnergyMetric metric_class;

    @Override
    public JPanel getSettingsPanel() {
        // We don't need a settings panel here...
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        this.metric_class = (FruEnergyMetric) statistics;
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
        return FruEnergyMetric.class;
    }

    @Override
    public String getValue() {
        return "Energy: " + metric_class.energy;
    }

    @Override
    public String getDisplayName() {
        return "Fru-Energy";
    }

    @Override
    public String getShortDescription() {
        return "Calculate the energy of the current layout according to Fru91.";
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
