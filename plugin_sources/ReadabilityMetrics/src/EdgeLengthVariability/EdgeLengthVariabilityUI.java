package EdgeLengthVariability;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsUI.class)
public class EdgeLengthVariabilityUI implements StatisticsUI {
    private EdgeLengthVariability metricClass;

    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        metricClass = (EdgeLengthVariability) statistics;
    }

    @Override
    public void unsetup() {
        // pass
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return EdgeLengthVariability.class;
    }

    @Override
    public String getValue() {
        return metricClass.result;
    }

    @Override
    public String getDisplayName() {
        return "Edge Length Variability";
    }
    
    @Override
    public String getShortDescription() {
        return "Calculate the coefficient of variation over all edge-lengths, to"
                + " quantify edge-length uniformity.";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_EDGE_OVERVIEW;
    }

    @Override
    public int getPosition() {
        return 800;
    }
}
