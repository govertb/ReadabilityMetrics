package NodeDistribution;

import EdgeLength.*;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class NodeDistributionMetricBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Metric for calculating uniformity of the distributed nodes.";
    }
    
    @Override
    public Statistics getStatistics() {
        return new NodeDistributionMetric();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return NodeDistributionMetric.class;
    }
}
