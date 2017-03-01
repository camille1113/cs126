/**
 * Created by zhanglanxin on 2/28/17.
 */
import com.google.gson.Gson;
import java.util.*;
import java.util.Map;


public class Graph implements AStarInterface<JsonCity> {
        private int [][] edgeWeight;
        private JsonCity [] nodes;
        private Map<String, Integer> nameToNodeIndexMap;

        Graph(String jsonString) {
            // parse the JSON
            Gson gson = new Gson();
            JsonGraph jsonGraph = gson.fromJson(jsonString, JsonGraph.class);
            // create a map from the name of the node to its index
            nodes = jsonGraph.getNodes();
            nameToNodeIndexMap = new HashMap<String, Integer>();
            for (int i = 0; i < nodes.length; i++) {
                nameToNodeIndexMap.put(nodes[i].getName(), i);
            }

            // create and populate the adjacency matrix
            int numNodes = nodes.length;
            edgeWeight = new int[numNodes][numNodes];       // default values are zeroes
            for (JsonEdge edge :
                    jsonGraph.getEdges()) {
                int node1 = nameToNodeIndexMap.get(edge.getNode1());
                int node2 = nameToNodeIndexMap.get(edge.getNode2());
                edgeWeight[node1][node2] = edge.getWeight();
                edgeWeight[node2][node1] = edge.getWeight();
            }
        }

        /**
         * @param rowNumber  the index of the desired node
         * @return array of values corresponding to distance to another node (0 means not a neighbor)
         */
        private int [] getRow(int rowNumber) {
            return edgeWeight[rowNumber];
        }

        /**
         * Recursively visit all of the nodes reachable from this node (transitive closure)
         * @param rowNumber     node to visit
         * @param visited       set of nodes that have been visited
         */
        private void visitAdjacent(int rowNumber, Set<Integer> visited) {
            if (visited.contains(rowNumber)) {
                return;
            }

            visited.add(rowNumber);
            int [] row = getRow(rowNumber);
            for (int i = 0; i < row.length; i++) {
                if (row[i] != 0) {
                    visitAdjacent(i, visited);
                }
            }
        }

        /**
         * @return  Is the graph a single connected component
         */
        public boolean isConnected() {
            // find all of the nodes reachable from node 0
            Set<Integer> visited = new HashSet<Integer>();
            visitAdjacent(0, visited);

            // graph is fully connected if we manage to visit every node
            return visited.size() == edgeWeight.length;
        }


        @Override
        public HashMap<JsonCity, Double> gScore() {
            HashMap<JsonCity, Double> gScore = new HashMap<>();
            for(JsonCity city:nodes){
                //initialize the gScore to infinity
                gScore.put(city, Double.MAX_VALUE);
            }
            return gScore;
        }

        @Override
        public HashMap<JsonCity, Double> fScore() {
            HashMap<JsonCity, Double> fScore = new HashMap<>();
            for(JsonCity city:nodes){
                //initialize the fScore to infinity
                fScore.put(city, Double.MAX_VALUE);
            }
            return fScore;
        }

        @Override
        public JsonCity getStart() {
            return nodes[0];
        }

        @Override
        public JsonCity getEnd() {
            return nodes[nodes.length-1];
        }

        @Override
        public double heuristicCost(JsonCity a, JsonCity b) {
            return a.distance(b);
        }

        @Override
        public Set<JsonCity> findNeighbor(JsonCity current) {
            Set<JsonCity> neighbors = new HashSet<>();
            int index = nameToNodeIndexMap.get(current.getName());
            for(int i = 0; i < nodes.length; i++){
                //if the edgeweight is 0, it is not a neighbor
                if(getRow(index)[i] != 0)
                    neighbors.add(nodes[i]);
            }
           return neighbors;
        }

    }

