package algorithm;

/*
 * @Author
 * Harsh Bhavsar
*/

public class Node{
	 
    public final String connectingPlace;
    public final String nodeName;
    public double g_value; // G function Value --> Current node to next node
    //public final double h_value; //Heuristic Function value --> Next node to straight line distance towards Goal
    
    public double h_value; //Heuristic Function value --> Next node to straight line distance towards Goal
    
    public double f_value = 0; // Total value of that node to heuristic distance towards goal f(n) = g(n) + h(n)
    public Edge[] adjacencies;
    public Node parentNode;
    


    public Node(String name, String value){
    	nodeName = name;
    	connectingPlace = value;
    }
    
    public Node(String name, String value, double hValue){
    	nodeName = name;
    	connectingPlace = value;
            h_value = hValue;
    }

    
    public String toString(){
            return connectingPlace;
    }

	public double getG_value() {
		return g_value;
	}

	public void setG_value(double g_value) {
		this.g_value = g_value;
	}

	public double getH_value() {
		return h_value;
	}

	public void setH_value(double h_value) {
		this.h_value = h_value;
	}

	public double getF_value() {
		return f_value;
	}

	public void setF_value(double f_value) {
		this.f_value = f_value;
	}

	public Edge[] getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(Edge[] adjacencies) {
		this.adjacencies = adjacencies;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public String getconnectingPlace() {
		return connectingPlace;
	}

	public String getNodeName() {
		return nodeName;
	}


}
