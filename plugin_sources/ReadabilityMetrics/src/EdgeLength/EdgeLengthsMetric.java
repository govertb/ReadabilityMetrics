package EdgeLength;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import utils.geometry;
import static utils.geometry.calculateEuclideanDistance;

public class EdgeLengthsMetric implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getGraph();
        graph.readLock();
        
        //assert false;
        
        // https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Online_algorithm
        int edge_count = 0;
        double mean = 0.0;
        double M2 = 0.0;
        double delta;
        
        for (Edge e : graph.getEdges()) {
            double length = getEdgeLength(e);
            edge_count++;
            delta = length - mean;
            mean = mean + delta / edge_count;
            M2 = M2 + delta * (length - mean);
        }
        
        double std_dev = Math.sqrt(M2 / (edge_count - 1));
        
        double variance_coefficient = std_dev / mean; //calculateCoefficientVariation(graph.getEdges().toArray());
        
//        Logger.getLogger(EdgeLengthsMetric.class.getName()).log(Level.INFO,
//            ">>>1:" + std_dev / mean
//        );
//        Logger.getLogger(EdgeLengthsMetric.class.getName()).log(Level.INFO,
//            ">>>2:" + variance_coefficient
//        );
        
        graph.readUnlockAll();
        if (edge_count < 2) {
            result = "Edge length variance can't be calculated from edge <= 2.";
        } else {
            result = String.valueOf(variance_coefficient);
        }
    }
    
    double calculateCoefficientVariation(Edge[] edges) {
        double sum = 0;
        for (Edge e : edges) {
           sum += getEdgeLength(e);
        }
        double mean = sum / edges.length;
        double difference_sum = 0;
        for (Edge e : edges) {
           difference_sum += Math.pow(getEdgeLength(e) - mean, 2);
        }
        double variance = difference_sum / edges.length;
        double std_dev = Math.sqrt(variance);
        double variance_coefficient = std_dev / mean;
        return variance_coefficient;
    }
    
    double getEdgeLength(Edge e) {
        return calculateEuclideanDistance(geometry.getEdgeCoords(e));
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