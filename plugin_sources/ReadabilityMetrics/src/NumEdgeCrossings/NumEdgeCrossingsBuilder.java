package NumEdgeCrossings;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsBuilder.class)
public class NumEdgeCrossingsBuilder implements StatisticsBuilder {
    @Override
    public String getName() {
        return "No. of Edge Crossings";
    }
    
    @Override
    public Statistics getStatistics() {
        return new NumEdgeCrossings();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return NumEdgeCrossings.class;
    }
}
