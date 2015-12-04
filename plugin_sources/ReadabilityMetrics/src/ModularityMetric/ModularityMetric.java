package ModularityMetric;

import NodeDistribution.NodeDistributionMetric;
import java.util.ArrayList;
import java.util.HashMap;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.statistics.plugin.Modularity;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import utils.geometry;
import utils.std_dev;


public class ModularityMetric implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public String result;
    
    HashMap<Integer, ArrayList<Node>> communities = new HashMap<Integer, ArrayList<Node>>();
   
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getGraph();
        graph.readLock();
        
        Modularity modularity = new Modularity();
        modularity.execute(gm, am);
        
        for (Node n: graph.getNodes()) {
            int partition = (Integer) (n.getAttributes().getValue("Modularity Class"));
            if (!communities.containsKey(partition)) {
                communities.put(partition, new ArrayList<Node>());
            }
            communities.get(partition).add(n);
        }
        
        ArrayList<Double> distances = new ArrayList<Double>();
        for (ArrayList<Node> com : communities.values()) {
            int size = com.size();
            for (int n_1 = 0; n_1 < size - 1; n_1++) {
                Node node1 = com.get(n_1);
                for (int n_2 = n_1 + 1; n_2 < size; n_2++) {
                    Node node2 = com.get(n_2);
                    distances.add(geometry.calculateEuclideanDistance(node1, node2));
                }
            }
        }
        
        double[] distance_array = new double[distances.size()];
        for (int i = 0; i < distance_array.length; i++) {
           distance_array[i] = distances.get(i);
        }
        double clusters_std_dev = std_dev.std_dev(distance_array);
        double total_std_dev = NodeDistributionMetric.calculate_std_dev(graph, false);
        
        result = String.valueOf(clusters_std_dev / total_std_dev);
        
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