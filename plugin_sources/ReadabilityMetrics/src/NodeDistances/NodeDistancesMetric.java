package NodeDistances;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import utils.geometry;

public class NodeDistancesMetric implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public String result;
    double average_total_distance;
    double average_neighbors_total_distance;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getGraph();
        graph.readLock();
        
        Node[] nodes = graph.getNodes().toArray();
        long total_distance = 0;
        for (int u_i = 0; u_i < nodes.length - 1; u_i++) {
            for (int v_i = u_i + 1; v_i < nodes.length; v_i++) {
                Node u = nodes[u_i];
                Node v = nodes[v_i];
                total_distance += geometry.calculateEuclideanDistance(u, v);
            }
        }
        average_total_distance = total_distance * 1.0 / graph.getNodeCount();
        
        long neighbors_total_distance = 0;
        for (Edge e : graph.getEdges()) {
            neighbors_total_distance += geometry.calculateEuclideanDistance(geometry.getEdgeCoords(e));
        }
        average_neighbors_total_distance = neighbors_total_distance * 1.0 / graph.getNodeCount();

        graph.readUnlockAll();
    }
    
    @Override
    public String getReport() {
        return "Average node distances: " + (average_neighbors_total_distance / average_total_distance) +
            " / " + ((average_total_distance - average_neighbors_total_distance) / average_total_distance);
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