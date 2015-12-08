package MetricUnifier;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsUI.class)
public class MetricUnifierUI implements StatisticsUI {
    private MetricUnifier metricClass;

    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        metricClass = (MetricUnifier) statistics;
    }

    @Override
    public void unsetup() {
        // pass
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
        return "Readability Metric Unifier";
    }

    @Override
    public String getShortDescription() {
        return "Executes all five readability metrics in a sequence.";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_NETWORK_OVERVIEW;
    }

    @Override
    public int getPosition() {
        return 800;
    }
}
