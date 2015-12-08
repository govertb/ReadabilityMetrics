package PlacementVariability;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsUI.class)
public class PlacementVariabilityUI implements StatisticsUI {
    private PlacementVariability metricClass;

    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        metricClass = (PlacementVariability) statistics;
    }

    @Override
    public void unsetup() {
        // pass
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return PlacementVariability.class;
    }

    @Override
    public String getValue() {
        return metricClass.result;
    }

    @Override
    public String getDisplayName() {
        return "Placement Variability";
    }

    @Override
    public String getShortDescription() {
        return "Calculate the coefficient of variation over the Euclidean "
                + "distances between all pairs of nodes.";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_NODE_OVERVIEW;
    }

    @Override
    public int getPosition() {
        return 800;
    }
}
