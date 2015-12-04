package FruEnergy;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.*;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import static utils.geometry.calculateEuclideanDistance;
import static utils.geometry.getNodeCoords;


class FruEnergyMetric implements Statistics, LongTask {
    private boolean cancel = false;
    ProgressTicket progressTicket;
    public double energy = 0;
//TODO: Correctly determine k...
    private double k = .5;  
    
    // Attractive force over distance d
    private double f_a(double d){
      return Math.pow(d, 2.0) / k;
    }

    // Repulsive force over distance d
    private double f_r(double d){
      if(d == 0){return Double.MAX_VALUE;}
      return Math.pow(k, 2) / d;
    }
    
    float[] getNodesBoundingBoxCoords(Graph graph) {
        float[] box_coords = new float[]{
            Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 
            Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY
        };
        for (Node n : graph.getNodes()) {
            float[] coords = getNodeCoords(n);
            box_coords[0] = Math.min(box_coords[0], coords[0]);
            box_coords[1] = Math.min(box_coords[1], coords[1]);
            box_coords[2] = Math.max(box_coords[2], coords[0]);
            box_coords[3] = Math.max(box_coords[3], coords[1]);
        }
        return box_coords;
    }
    
    // Return distance between nodes n1, n2
    // BV: volgens mij klopt dit niet ouwe, je kan 
    // gebruiken hiervoor.
    private double node_dist(Node n1, Node n2){
        float[] n1_coord = {n1.getNodeData().x(), n1.getNodeData().y()};
        float[] n2_coord = {n2.getNodeData().x(), n2.getNodeData().y()};
        return calculateEuclideanDistance(n1_coord, n2_coord);
    }
    
    @Override
    public void execute(GraphModel gm, AttributeModel am) {
        Graph graph = gm.getGraph();
        graph.readLock();
        
        this.energy = 0.0;
        for(Node n1 : graph.getNodes()){
            for(Node n2 : graph.getNodes()){
                if(n1 == n2){continue;}
                    this.energy += f_a(node_dist(n1, n2)) + f_r(node_dist(n1, n2));
            }
        }    
        graph.readUnlockAll();
    }
    

    @Override
    public String getReport() {
        // We don't need a report.
        return null;
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