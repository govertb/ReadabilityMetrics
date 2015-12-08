package PlacementVariability;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsBuilder.class)
public class PlacementVariabilityBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Placement Variability";
    }
    
    @Override
    public Statistics getStatistics() {
        return new PlacementVariability();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return PlacementVariability.class;
    }
}
