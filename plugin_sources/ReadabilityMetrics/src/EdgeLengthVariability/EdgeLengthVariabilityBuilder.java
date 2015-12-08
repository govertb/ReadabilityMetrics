package EdgeLengthVariability;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsBuilder.class)
public class EdgeLengthVariabilityBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Edge Length Variability";
    }
    
    @Override
    public Statistics getStatistics() {
        return new EdgeLengthVariability();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return EdgeLengthVariability.class;
    }
}
