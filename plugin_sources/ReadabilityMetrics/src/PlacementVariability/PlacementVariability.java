package PlacementVariability;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import utils.geometry;
import utils.math;

/*  Benjamin Versteeg & Govert Brinkman (2015)
    Calculates the node placement variability by taking the coefficient of 
    variation over the Euclidean distances between all pairs of nodes. This way 
    is chosen over the standard deviation to prevent bias towards a shrunken or 
    a stretched graph. A high placement variability thus indicates nodes are 
    evenly distributed, whereas a low placement variability indicates the opposite.
*/

public class PlacementVariability implements Statistics {
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getUndirectedGraphVisible();
        graph.readLock();
        
        // calculate all Euclidean distances between all nodes
        double[] distances = geometry.calculateManyEuclideanDistances(
            graph.getNodes().toArray()
        );
        // calculate the variation of coefficient over the distances
        result = String.valueOf(
            math.variationCoefficient(distances)
        );
        graph.readUnlockAll();
    }
    
    @Override
    public String getReport() {
        return result;
    }
}