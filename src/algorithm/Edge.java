package algorithm;

public class Edge{
    public final double cost; // Cost to that node
    public final Node target; //Will describe our next target node

    public Edge(Node targetNode, double costValue){
            target = targetNode;
            cost = costValue;
    }
}