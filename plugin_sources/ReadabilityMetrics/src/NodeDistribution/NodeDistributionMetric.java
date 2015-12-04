package NodeDistribution;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import utils.geometry;

public class NodeDistributionMetric implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getGraph();
        result = String.valueOf(calculate_std_dev(graph, true));
    }
    
    public static double calculate_std_dev(Graph graph, boolean variance_coefficient) {
        graph.readLock();
        // https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Online_algorithm

        int node_count = 0;
        double mean = 0.0;
        double M2 = 0.0;
        double delta;
        Node[] nodes = graph.getNodes().toArray();
        
        for (int node_i = 0; node_i < nodes.length; node_i++) {
            for (int node_j = node_i + 1; node_j < nodes.length; node_j++) {
                node_count++;
                double distance = geometry.calculateEuclideanDistance(nodes[node_i], nodes[node_j]);            
                delta = distance - mean;
                mean = mean + delta / node_count;
                M2 = M2 + delta * (distance - mean);
            }
        }
        graph.readUnlockAll();

        if (node_count < 2) {
            return -9.99999;
        } else {
            double std_dev = Math.sqrt(M2 / (node_count - 1));
            if (variance_coefficient)
                return std_dev / mean;
            return std_dev;
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