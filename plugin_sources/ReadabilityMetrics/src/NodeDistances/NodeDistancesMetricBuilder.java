package NodeDistances;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class NodeDistancesMetricBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Calculate the average distance between all nodes, neighbors and not neighborin nodes.";
    }
    
    @Override
    public Statistics getStatistics() {
        return new NodeDistancesMetric();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return NodeDistancesMetric.class;
    }
}
