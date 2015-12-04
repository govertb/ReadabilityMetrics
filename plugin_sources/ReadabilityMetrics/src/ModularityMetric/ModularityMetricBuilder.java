package ModularityMetric;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class ModularityMetricBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Metric for calculating the degree of separation of communities.";
    }
    
    @Override
    public Statistics getStatistics() {
        return new ModularityMetric();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return ModularityMetric.class;
    }
}
