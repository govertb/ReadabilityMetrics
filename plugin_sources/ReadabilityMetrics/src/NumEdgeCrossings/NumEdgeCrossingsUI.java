package NumEdgeCrossings;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsUI.class)
public class NumEdgeCrossingsUI implements StatisticsUI {
    private NumEdgeCrossings metricClass;

    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        this.metricClass = (NumEdgeCrossings) statistics;
    }

    @Override
    public void unsetup() {
        // pass
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return NumEdgeCrossings.class;
    }

    @Override
    public String getValue() {
        return metricClass.result;
    }

    @Override
    public String getDisplayName() {
        return "No. of Edge Crossings";
    }

    @Override
    public String getShortDescription() {
        return "Calculate to what degree a graph is visual \"unclear\" by "
                + "counting the number of crossings/intersections between all edges";
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
