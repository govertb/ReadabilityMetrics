package EdgeCrossings;

import java.awt.geom.Line2D;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import utils.geometry;

public class EdgeCrossingsMetric implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getGraph();
        graph.readLock();
        
        int num_crossings = 0;
        Edge[] edges = graph.getEdges().toArray();
        for (int u_i = 0; u_i < edges.length - 1; u_i++) {
            for (int v_i = u_i + 1; v_i < edges.length; v_i++) {
                Edge u = edges[u_i];
                Edge v = edges[v_i];
                if (edgesShareNode(u, v)) continue;
                if (edgesIntersect(u, v)) {
                    num_crossings++;
                    v.getEdgeData().setColor(1.0f, 0.0f, 0.0f);
                    u.getEdgeData().setColor(1.0f, 0.0f, 0.0f);
                }
            }
        }
        result = String.valueOf(num_crossings);
        graph.readUnlockAll();
    }
    
    static boolean edgesShareNode(Edge u, Edge v) {
        return (
            u.getSource() == v.getSource() || 
            u.getSource() == v.getTarget() ||
            u.getTarget() == v.getTarget() ||
            u.getTarget() == v.getSource()
        );
    }
    
    private boolean edgesIntersect(Edge u, Edge v){
        float[] u_coords = geometry.getEdgeCoords(u);
        float[] v_coords = geometry.getEdgeCoords(v);
        return Line2D.linesIntersect(
            u_coords[0], u_coords[1], u_coords[2], u_coords[3],
            v_coords[0], v_coords[1], v_coords[2], v_coords[3]
        );
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