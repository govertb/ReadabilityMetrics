package CommunityBlending;

import java.util.ArrayList;
import java.util.HashMap;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.statistics.plugin.Modularity;
import org.gephi.statistics.spi.Statistics;
import utils.geometry;
import utils.math;

/*  Benjamin versteeg & Govert Brinkman (2015)
    Measure the degree to which different communities are separated (visually). 
    First all communities are determined using Modularity, with its default 
    resolution of 1.0. Then the standard deviation over all intra-community node
    pair distances (Euclidean) is considered relatively to the standard 
    deviation over all node pair distances (Euclidean). This fraction the 
    measure of how much communities are (geometrically) separated. Smaller 
    values for the community blending metric indicate that communities are 
    better separated (geometrically), whereas larger values indicate they are 
    not.

    Note: this metric is very sensitive to disconnected components. We highly
    advise to apply this metric only to the giant component.
*/

public class CommunityBlending implements Statistics {
    // Number of times to repeat interCommunityDistanceStdDev method.
    static final int NUM_RUNS = 10;
    public String result;
    Graph graph;
   
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        // Use the visible graph, so filtered disconnected components are excluded.
        graph = gm.getUndirectedGraphVisible();
        graph.readLock();
        
        // Calculate standard deviation of all Euclidean node distances.
        double allDistancesStdDev = math.standardDeviation(
            geometry.calculateManyEuclideanDistances(
                graph.getNodes().toArray()
            )
        );
        
        // We calculate the inter community distances standard deviation 10 times,
        // and average the result. Modularity returns slighty different communities
        // with each run.
        double[] communityDistances = new double[NUM_RUNS];
        for (int run_i = 0; run_i < NUM_RUNS; run_i++) {
            communityDistances[run_i] = interCommunityDistanceStdDev(gm, am);
        }
        
        // Finally devide the averaged inter community standard deviations by the
        // standard deviation of all distances.
        result = String.valueOf(
            math.average(communityDistances) / allDistancesStdDev
        );
        
        graph.readUnlockAll();
    }
    
    // Method which will calculate the standard deviation of the all node pair
    // distances per community.
    private double interCommunityDistanceStdDev(GraphModel gm, AttributeModel am) {
        // Hashmap for storing the nodes per community class.
        HashMap<Integer, ArrayList<Node>> communities = new HashMap<Integer, ArrayList<Node>>();
        
        // Invoke modularity with default parameters.
        Modularity modularity = new Modularity();
        modularity.execute(gm, am);
        
        // For each node in the (visible graph) we group them per community.
        for (Node n: graph.getNodes()) {
            int partition = (Integer) (n.getAttributes().getValue("Modularity Class"));
            if (!communities.containsKey(partition)) {
                communities.put(partition, new ArrayList<Node>());
            }
            communities.get(partition).add(n);
        }
        
        // Array list of storing all node pair distances per community.
        ArrayList<Double> allCommunityDistances = new ArrayList<Double>();
        // For each community
        for (ArrayList<Node> communityNodes : communities.values()) {
            // Calculate the Euclidean distances for all node pairs in the community.
            double[] comDistances = geometry.calculateManyEuclideanDistances(
                communityNodes.toArray(new Node[communityNodes.size()])
            );
            // Add acquired distances to the array of all distances.
            for (double d : comDistances) {
                allCommunityDistances.add(d);
            }
        }
        
        // Convert the ArryList<Double> to double[]
        double[] allCommunityDistancesArray = new double[allCommunityDistances.size()];
        for (int i = 0; i < allCommunityDistancesArray.length; i++) {
           allCommunityDistancesArray[i] = allCommunityDistances.get(i);
        }
        
        // return the standard deviation of all distances
        return math.standardDeviation(allCommunityDistancesArray);
    }
   
    @Override
    public String getReport() {
        return result;
    }
}