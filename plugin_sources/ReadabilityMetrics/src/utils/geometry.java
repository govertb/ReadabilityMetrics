package utils;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeData;

public class geometry {
    public static double calculateEuclideanDistance(float[] coords) {
        return (double) Math.sqrt(
            Math.pow(coords[0] - coords[2], 2) + Math.pow(coords[1] - coords[3], 2)
        );
    }
    
    public static double calculateEuclideanDistance(Node n1, Node n2) {
        return calculateEuclideanDistance(getNodeCoords(n1), getNodeCoords(n2));
    }
    
    public static double calculateEuclideanDistance(float[] coords1, float[] coords2) {
        return (double) Math.sqrt(
            Math.pow(coords1[0] - coords2[0], 2) + Math.pow(coords1[1] - coords2[1], 2)
        );
    }
    
    public static float[] getNodeCoords(Node n) {
        NodeData data = n.getNodeData();
        return new float[] {data.x(), data.y()};
    }
    
    public static float[] getEdgeCoords(Edge e) {
        NodeData source1 = e.getSource().getNodeData();
        NodeData target1 = e.getTarget().getNodeData();
        return new float[] {
            source1.x(), source1.y(), target1.x(), target1.y()
        };
    }
}
