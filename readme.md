Readability Metrics
===================

A certain graph can be less or more 'readable'; information contained in a graph can be percepted with a degree ease. In order to quantify to which degree a certain graph is 'readable', we defined a set of five so called readability metrics. The metrics are developed as plugins for the network visualization tool called Gephi.  See [the Gephi Wiki](https://github.com/gephi/gephi/wiki/Plugin-Quick-Start "Gephi Plugin Quick Start") for information on how to install these.<br/>

**Readability metrics:**
- No. of edge-crossings 
- Edge-length variability
- Placement variability
- Neighbor separation
- Community Blending

***No. of edge-crossings***
Quantify the degree to which a graph is visually blurred due to edges by counting the number of crossings/intersections between all edges.

***Edge-length variability***
Quantify edge-length uniformity by determining edge length variability.  This is done by calculating the coefficient of variation over all edge-lengths. We choose this statistic over, e.g., the standard deviation or variance since it is normalized and thus allows us to compare regardless of the size of the frame in which the graph is drawn. A low coefficient would indicate little variability (uniformity), whereas a high coefficient would indicate much variability (non-uniformity).

***Placement variability***
Calculates the node placement variability by taking the coefficient of variation over the Euclidean distances between all pairs of nodes. This way is chosen over the standard deviation to prevent bias towards a shrunken or a stretched graph. A high placement variability thus indicates nodes are evenly distributed, whereas a low placement variability indicates the opposite.

***Neighbor separation***
Measure the degree to which neighbors are grouped by determining the average distance between all nodes connected by an edge (neighbors). To prevent bias towards a shrunken or a stretched graph the obtained average is divided by the average distance between all node pairs. A high neighbor separation implies neighbors are poorly grouped, whereas a low neighbor separation implies the opposite.

**Community Blending**
Measure the degree to which different communities are separated (visually). First all communities are determined using Modularity, with its default resolution of 1.0. Then the standard deviation over all intra-community node pair distances (Euclidean) is considered relatively to the standard deviation over all node pair distances (Euclidean). This fraction the measure of how much communities are (geometrically) separated. Smaller values for the community blending metric indicate that communities are better separated (geometrically), whereas larger values indicate they are not.

Finally, a plugin is contained, called *Metric Unifier*, which allows sequential execution of all five metrics. Which might be convenient in case of high repetition or a large graph (long waiting time).