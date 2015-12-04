package NodeOverlap;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import static utils.geometry.calculateEuclideanDistance;
import static utils.geometry.getNodeCoords;

public class NodeOverlapMetric implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getGraph();
        graph.readLock();
        
        int node_count = graph.getNodeCount();
        float[][] node_coords = new float[node_count][];
        float[] node_radi = new float[node_count];
        {
            int i = 0;
            for (Node n : graph.getNodes()) {
                node_radi[i] = n.getNodeData().getRadius();
                node_coords[i++] = getNodeCoords(n);
            }
        }
        
        int overlapping_nodes = 0;
        for (int i = 0; i < node_count - 1; i++) {
            float[] coords1 = node_coords[i];
            float radius1 = node_radi[i];
            for (int j = i + 1; j < node_count; j++) {
                float[] coords2 = node_coords[j];
                float radius2 = node_radi[j];
                double distance = calculateEuclideanDistance(coords1, coords2);
                if (distance < radius1 + radius2) {
                    overlapping_nodes++;
                }
            }
        }
        result = String.valueOf(overlapping_nodes * 1.0 / node_count);
        graph.readUnlockAll();
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