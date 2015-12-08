package EdgeLengthVariability;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import utils.geometry;
import utils.math;

/*  Benjamin Versteeg & Govert Brinkman (2015)
    Quantify edge-length uniformity by determining edge length variability. 
    This is done by calculating the coefficient of variation over all edge-lengths.
    We choose this statistic over, e.g., the standard deviation or variance since 
    it is normalized and thus allows us to compare regardless of the size of the
    frame in which the graph is drawn. A low coefficient would indicate little 
    variability (uniformity), whereas a high coecient would indicate much 
    variability (non-uniformity).
*/

public class EdgeLengthVariability implements Statistics {
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getUndirectedGraphVisible();
        graph.readLock();
        
        // Calculate the variation coefficient over all edge lengths
        double variationCoefficient = math.variationCoefficient(
            geometry.calculateManyEuclideanDistances(
                graph.getEdges().toArray()
            )
        );
        result = String.valueOf(variationCoefficient);
        graph.readUnlockAll();
    }
 
    @Override
    public String getReport() {
        return result;
    }
}