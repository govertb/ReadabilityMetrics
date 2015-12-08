package CommunityBlend;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsUI.class)
public class CommunityBlendUI implements StatisticsUI {
    private CommunityBlend metricClass;

    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
        metricClass = (CommunityBlend) statistics;
    }

    @Override
    public void unsetup() {
        // pass
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return CommunityBlend.class;
    }

    @Override
    public String getValue() {
        return metricClass.result;
    }

    @Override
    public String getDisplayName() {
        return "Community Blend";
    }

    @Override
    public String getShortDescription() {
        return "Measure the degree to which different communities are separated visually.";
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
