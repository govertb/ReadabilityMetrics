package MetricUnifier;

import NodeOverlap.*;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class MetricUnifierBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Metric for calculating the fraction of overlapping nodes.";
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
