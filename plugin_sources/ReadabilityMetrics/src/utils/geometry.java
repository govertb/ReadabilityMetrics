package utils;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeData;

/*  Benjamin Versteeg & Govert Brinkman (2015) 
    Various geometrical methods to apply to either Gephi's node or edge instances,
    or just float coordinates.
*/

public class geometry {
    // Calculates Euclidean distance between two coordinates in a float[4] array
    public static double calculateEuclideanDistance(float[] coords) {
        assert coords.length == 4;
        return Math.sqrt(
            Math.pow(coords[0] - coords[2], 2) + 
            Math.pow(coords[1] - coords[3], 2)
        );
    }
    
    // Calculates Euclidean distance between two Node instances
    public static double calculateEuclideanDistance(Node n1, Node n2) {
        return calculateEuclideanDistance(
            getNodeCoords(n1), 
            getNodeCoords(n2)
        );
    }
    
    // Calculates Euclidean distance between two coordinates in two float[2] arrays
    public static double calculateEuclideanDistance(float[] coords1, float[] coords2) {
        return Math.sqrt(
            Math.pow(coords1[0] - coords2[0], 2) + 
            Math.pow(coords1[1] - coords2[1], 2)
        );
    }
    
    // Calculates Euclidean distances (lengths) of all edges supplied.
    public static double[] calculateManyEuclideanDistances(Edge[] edges) {
        double[] distances = new double[edges.length];
        int i = 0;
        for (Edge e : edges) {
            distances[i++] = calculateEuclideanDistance(
                getEdgeCoords(e)
            );
        }
        return distances;
    }
    
    // Calculates Euclidean distances between all node pairs of nodes supplied.
    public static double[] calculateManyEuclideanDistances(Node[] nodes) {
        double[] distances = new double[nodes.length * (nodes.length - 1) / 2];
        int distance_i = 0;
        for (int node_i = 0; node_i < nodes.length; node_i++) {
            for (int node_j = node_i + 1; node_j < nodes.length; node_j++) {
                distances[distance_i++] = calculateEuclideanDistance(
                    nodes[node_i], 
                    nodes[node_j]
                );
            }
        }
        return distances;
    }
    
    // Acquire float[2] coordinate of a certain Node instance
    public static float[] getNodeCoords(Node n) {
        NodeData data = n.getNodeData();
        return new float[] {data.x(), data.y()};
    }
    
    // Acquire two coordinates (float[4]) of a certain Edge instance
    public static float[] getEdgeCoords(Edge e) {
        NodeData source1 = e.getSource().getNodeData();
        NodeData target1 = e.getTarget().getNodeData();
        return new float[] {
            source1.x(), source1.y(), 
            target1.x(), target1.y()
        };
    }
}
