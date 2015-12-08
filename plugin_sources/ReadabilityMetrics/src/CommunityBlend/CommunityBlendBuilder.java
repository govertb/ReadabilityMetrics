package CommunityBlend;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

/*  Benjamin Versteeg & Govert Brinkman (2015) */

@ServiceProvider(service = StatisticsBuilder.class)
public class CommunityBlendBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Community Blend";
    }
    
    @Override
    public Statistics getStatistics() {
        return new CommunityBlend();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return CommunityBlend.class;
    }
}
