package MetricUnifier;

import EdgeCrossings.EdgeCrossingsMetric;
import ModularityMetric.ModularityMetric;
import NodeOverlap.*;
import EdgeLength.*;
import NodeDistances.*;
import NodeDistribution.NodeDistributionMetric;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;


class MetricUnifier implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Statistics[] metrics = new Statistics[] {
            new EdgeLengthsMetric(), 
            new EdgeCrossingsMetric(),
            new NodeDistancesMetric(),
            new NodeDistributionMetric(),
            new ModularityMetric()
        };
        String[] metric_result_prefixes = {
            "Edge length standard deviation: ",
            "Edge crossings: ",
            "",
            "Node distribution: ",
            "Modularity separation: "
        };
        
        result = "";
        for (int metric_i = 0; metric_i < metrics.length; metric_i++) {
            Statistics metric = metrics[metric_i];
            System.out.println("Executing readability: " + metric.getClass().getName());
            metric.execute(gm, am);
            result += metric_result_prefixes[metric_i] + metric.getReport() + "\n";
        }
    }

    @Override
    public String getReport() {
        return result;
    }

    @Override
    public boolean cancel() {
        cancel = true;
        return true;
    }

    @Override
    public void setProgressTicket(ProgressTicket pt) {
        progressTicket = pt;
    }
}