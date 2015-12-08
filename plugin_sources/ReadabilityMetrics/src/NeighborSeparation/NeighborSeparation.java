package NeighborSeparation;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import utils.geometry;
import utils.math;

/*  Benjamin Versteeg & Govert Brinkman (2015)
    Measure the degree to which neighbors are grouped by determining the average
    distance between all nodes connected by an edge (neighbors). To prevent bias
    towards a shrunken or a stretched graph the obtained average is divided by 
    the average distance between all node pairs. A high neighbor separation 
    implies neighbors are poorly grouped, whereas a low neighbor separation 
    implies the opposite.
*/

public class NeighborSeparation implements Statistics {
    public String result;
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getUndirectedGraphVisible();
        graph.readLock();
        
        // Calculate the average over all Euclidean distances between all 
        // node pairs.
        double allDistancesAverage = math.average(
            geometry.calculateManyEuclideanDistances(
                graph.getNodes().toArray()
            )
        );
        
        // Calculate the average over all Euclidean distances between all
        // neighbors.
        double neighborDistancesAverage = math.average(
            geometry.calculateManyEuclideanDistances(
                graph.getEdges().toArray()
            )
        );
        
        // Finally divide the two averages --> neighbor separation measure
        result = String.valueOf(neighborDistancesAverage / allDistancesAverage);
        graph.readUnlockAll();
    }
    
    @Override
    public String getReport() {
        return result;
    }
}