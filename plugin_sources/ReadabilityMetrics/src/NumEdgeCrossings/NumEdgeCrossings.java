package NumEdgeCrossings;

import java.awt.geom.Line2D;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import utils.geometry;

/*  Benjamin Versteeg & Govert Brinkman (2015)
    Quantify the degree to which a graph is visually blurred due to edges by 
    counting the number of crossings/intersections between all edges.
*/

public class NumEdgeCrossings implements Statistics {
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getUndirectedGraphVisible();
        graph.readLock();
        
        // counter for number of crossings
        int numCrossings = 0;
        
        Edge[] edges = graph.getEdges().toArray();
        
        // Equivalent of for each over all edge pairs/combinations:
        for (int u_i = 0; u_i < edges.length - 1; u_i++) {
            Edge u = edges[u_i];
            for (int v_i = u_i + 1; v_i < edges.length; v_i++) {
                Edge v = edges[v_i];
                
                // Check if the two edges share at least one node, if so continue
                // to next edge; such edges will visually not cross. But Java's 
                // Line2D.linesIntersect would return true due to the very minimal
                // actual overlap between the lines.
                if (edgesShareNode(u, v)) continue;
                
                if (edgesIntersect(u, v)) {
                    numCrossings++;
                    
                    // Edges that cross are colored red.
                    v.getEdgeData().setColor(1.0f, 0.0f, 0.0f);
                    u.getEdgeData().setColor(1.0f, 0.0f, 0.0f);
                }
            }
        }
        result = String.valueOf(numCrossings);
        graph.readUnlockAll();
    }
    
    static boolean edgesShareNode(Edge u, Edge v) {
        // Check if the two edges share at least one node, if so continue
        // to next edge; such edges will visually not cross. But Java's 
        // Line2D.linesIntersect would return true due to the very minimal
        // actual overlap between the lines.
        
        return (
            u.getSource() == v.getSource() || 
            u.getSource() == v.getTarget() ||
            u.getTarget() == v.getTarget() ||
            u.getTarget() == v.getSource()
        );
    }
    
    // Method which check whether two specified edges intersect.
    private boolean edgesIntersect(Edge u, Edge v){
        // Acquire the coordinates of both edges
        float[] u_coords = geometry.getEdgeCoords(u);
        float[] v_coords = geometry.getEdgeCoords(v);
        
        // The following Java's builtin function allows easy intersection check.
        return Line2D.linesIntersect(
            u_coords[0], u_coords[1], u_coords[2], u_coords[3],
            v_coords[0], v_coords[1], v_coords[2], v_coords[3]
        );
    }
    
    @Override
    public String getReport() {
        return result;
    }
}