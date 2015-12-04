package EdgeLength;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class EdgeLengthsMetricBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Metric for calculating the number of edge pair that intersect/cross.";
    }
    
    @Override
    public Statistics getStatistics() {
        return new EdgeLengthsMetric();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return EdgeLengthsMetric.class;
    }
}
