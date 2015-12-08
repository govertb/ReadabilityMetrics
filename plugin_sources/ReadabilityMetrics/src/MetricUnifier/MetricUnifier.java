package MetricUnifier;

import EdgeLengthVariability.EdgeLengthVariability;
import NeighborSeparation.NeighborSeparation;
import NumEdgeCrossings.NumEdgeCrossings;
import CommunityBlend.CommunityBlend;
import PlacementVariability.PlacementVariability;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;

/*  Benjamin Versteeg & Govert Brinkman (2015)
    This plugin simply joins the following five readability metric plugins together:
        - Edge length variability
        - No. of edge crossings
        - Placement variability
        - Neighbor separation
        - Community blend

    This allows a sequential execution, which might be convenient in case of 
    high repetition or a large graph (long waiting time).
*/

class MetricUnifier implements Statistics {
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        // Readability metric classes/instances to execute:
        Statistics[] metricInstances = new Statistics[] {
            new EdgeLengthVariability(), 
            new NumEdgeCrossings(),
            new PlacementVariability(),
            new NeighborSeparation(),
            new CommunityBlend()
        };
        
        // Labels used in the results:
        String[] metricResultLabels = {
            "Edge length variability: ",
            "No. of edge crossings: ",
            "Placement variability: ",
            "Neighbor separation: ",
            "Community blend: "
        };
        
        // Execute each metric and join the results
        StringBuilder resultBuilder = new StringBuilder();
        for (int metric_i = 0; metric_i < metricInstances.length; metric_i++) {
            Statistics metric = metricInstances[metric_i];
            metric.execute(gm, am);
            resultBuilder.append(metricResultLabels[metric_i]);
            resultBuilder.append(metric.getReport());
            resultBuilder.append(System.getProperty("line.separator"));
        }
        result = resultBuilder.toString();
    }

    @Override
    public String getReport() {
        return result;
    }
}