package MetricUnifier;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsBuilder.class)
public class MetricUnifierBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Readability Metric Unifier";
    }
    
    @Override
    public Statistics getStatistics() {
        return new MetricUnifier();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return MetricUnifier.class;
    }
}
