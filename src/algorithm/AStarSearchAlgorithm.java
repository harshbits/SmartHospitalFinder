package algorithm;

/*
 * @author Harsh H Bhavsar
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

//Implementation of A* Algorithm
public class AStarSearchAlgorithm {

	
	//This method will print the path to the particular location
	//It will store the node in list, and the value of node will be printed finally.
	public List<Node> printPath(Node target) {
		List<Node> path = new ArrayList<Node>();

		for (Node node = target; node != null; node = node.parentNode) {
			path.add(node);
		}
		Collections.reverse(path);

		return path;
	}

	//A* search algorithm Implementation
	public void AstarSearchAlgo(Node source, Node goal) {

		Set<Node> explored = new HashSet<Node>();

		//Using priority queue it will compare the node as per the requirement (imput)
		PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
				new Comparator<Node>() {
					public int compare(Node i, Node j) {
						if (i.f_value > j.f_value) {
							return 1;
						}
						else if (i.f_value < j.f_value) {
							return -1;
						}

						else {
							return 0;
						}
					}
				});
		source.g_value = 0;
		queue.add(source);

		boolean found = false;

		while ((!queue.isEmpty()) && (!found)) {
			Node current = queue.poll();

			explored.add(current);
			if (current.connectingPlace.equals(goal.connectingPlace)) {
				found = true;
			}

			for (Edge e : current.adjacencies) {
				Node child = e.target;
				double cost = e.cost;
				double temp_g_scores = current.g_value + cost;
				double temp_f_scores = temp_g_scores + child.h_value;
				if ((explored.contains(child))
						&& (temp_f_scores >= child.f_value)) {
					continue;
				}

				//Calculation of Heuristic as per the psuedo code
				else if ((!queue.contains(child))
						|| (temp_f_scores < child.f_value)) {

					child.parentNode = current;
					child.g_value = temp_g_scores;
					child.f_value = temp_f_scores;

					if (queue.contains(child)) {
						queue.remove(child);
					}

					queue.add(child);

				}
			}
		}
	}
}
