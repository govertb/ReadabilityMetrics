package FruEnergy;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class FruEnergyMetricBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Calculates 'energy of the layout' according to fru91.";
    }
    
    @Override
    public Statistics getStatistics() {
        return new FruEnergyMetric();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return FruEnergyMetric.class;
    }
}
