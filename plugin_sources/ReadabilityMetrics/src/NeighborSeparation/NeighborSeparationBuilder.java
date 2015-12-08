package NeighborSeparation;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsBuilder.class)
public class NeighborSeparationBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Neighbor Separation";
    }
    
    @Override
    public Statistics getStatistics() {
        return new NeighborSeparation();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return NeighborSeparation.class;
    }
}
