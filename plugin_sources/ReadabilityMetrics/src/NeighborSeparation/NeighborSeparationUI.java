package NeighborSeparation;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsUI.class)
public class NeighborSeparationUI implements StatisticsUI {
    private NeighborSeparation metricClass;

    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        metricClass = (NeighborSeparation) statistics;
    }

    @Override
    public void unsetup() {
        // pass
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return NeighborSeparation.class;
    }

    @Override
    public String getValue() {
        return metricClass.result;
    }

    @Override
    public String getDisplayName() {
        return "Neighbor Separation";
    }

    @Override
    public String getShortDescription() {
        return "Measure the degree to which neighbors are grouped by determining"
                + "the average distance between all nodes connected by an edge";
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
