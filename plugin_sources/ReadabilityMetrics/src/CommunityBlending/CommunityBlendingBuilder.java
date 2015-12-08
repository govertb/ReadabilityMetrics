package CommunityBlending;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsBuilder.class)
public class CommunityBlendingBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Community Blending";
    }
    
    @Override
    public Statistics getStatistics() {
        return new CommunityBlending();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return CommunityBlending.class;
    }
}
