package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

 
public class AstarSearchAlgo{
 
 /*
        //h scores is the stright-line distance from the current city to Bucharest
        public static void main(String[] args){
 
        		Node BFC = new Node("Baylor Medical Center at Frisco", 7.284);
        		Node CHATHM = new Node("Chatham Courts", 2.182);
        		Node CMPCRD = new Node("W Campbell Road + Coit Road", 2.878);
        		Node CRDJBH = new Node("Coit Road + J Bush Highway", 1.376);	
        		Node CRDWSP = new Node("Coit Road + W Spring Creek Pkwy", 2.561);
        		Node DIKFRRD = new Node("Dikerson + Frankford Road", 2.537);      
        		Node DNTWRP = new Node("Dallas Northway Tollway + Warren Pkwy", 6.872);
        		Node FRRDCRD = new Node("Frankford Road + Coit Road", 1.541);
        		Node HHB = new Node("THe baylor heart hospital", 1.377);
        		Node HILFRD = new Node("Hilcrest Road + Frankford Road", 1.828);
        		Node JBHDNT = new Node("J Bush Highway + Dallas Northway Tollway", 3.665);
        		Node MCCCRD = new Node("McCallum Blvd + Coit Road", 2.185);
        		Node MCCDIK = new Node("McCallum Blvd + Dikerson", 2.214);
        		Node MCCHIL = new Node("McCallum blvd + Hilcrest Road", 2.401);
        		Node MCCMND = new Node("McCallum Blvd + Meandering Way", 2.415);
        		Node MCP = new Node("Medical Center of Plano", 0);
        		Node MNDFRD = new Node("Meandering Way + Frankford Road", 1.728);
        		Node OHIOAID = new Node("Ohio Dr + Allied Dr", 1.219);
            	Node UTD = new Node("The University of Texas at Dallas", 2.689);
        		Node UTDWW = new Node("UTD + Waterview Parkway", 2.449);
        		Node WPKCRD = new Node("W Plano pkwy + Coit Road", 0.671);
        		Node WSPDNT = new Node("W Spring Creek Pkwy + Dallas Northway Tollway", 4.643);
        		
        		
        		Node WWFRRD = new Node("Waterview Parkway + Frankford Road", 1.718);
        		
        	
        		
        		
        		Node WWCMP = new Node("Waterview Parkway + W Campbell Road", 2.941);
        		
        		
        		
        		Node WWWPK = new Node("Waterview Parkway + W Plano pkwy",1.221);
        		
        		
        		
        		
        		
        		
        		
        		
        		
        	
        		
        		
        		
        		
        		
        		for(int i=0;i<10;i++){
        			
        			
        			
        			
        		}
        		
        		
        		
        		UTD.adjacencies = new Edge[]{
        				
        				new Edge(UTDWW,0.5)
        			};

        		UTDWW.adjacencies = new Edge[]{
        				new Edge(UTD, 0.5),
        				new Edge(WWCMP, 0.5),
        				new Edge(WWFRRD, 0.8)
        			};
        		
        		WWFRRD.adjacencies = new Edge[]{
        				new Edge(UTDWW, 0.8),
        				new Edge(WWWPK, 0.8),
        				new Edge(FRRDCRD, 0.8)
        			};

        		FRRDCRD.adjacencies = new Edge[]{
        				new Edge(WWFRRD,0.8),
        				new Edge(MCCCRD,0.6),
        				new Edge(DIKFRRD,0.4),
        				new Edge(CRDJBH, 0.5)
        		};
        		
        		CRDJBH.adjacencies = new Edge[]{
        				new Edge(FRRDCRD, 0.5),
        				//new Edge(CRDWSP, 3.6),
        				new Edge(WPKCRD, 0.4),
        				new Edge(JBHDNT,3.1)
        				
        			};
        		
        		JBHDNT.adjacencies = new Edge[]{
        				new Edge(CRDJBH, 3.1),
        				new Edge(WSPDNT, 3.5)
        		};
        		
        		WSPDNT.adjacencies = new Edge[]{
        				
        				new Edge(DNTWRP, 2.8),
        				new Edge(CRDWSP, 3.4),
        				new Edge(JBHDNT,3.5)
        		};
        		
        		
        		DNTWRP.adjacencies = new Edge[]{
        				
        				new Edge(BFC, 1.1),
        				new Edge(WSPDNT,2.8)
        			};
        		

        		CRDWSP.adjacencies = new Edge[]{
        				
        				new Edge(WSPDNT, 3.4),
        				new Edge(MCP, 2.5)
        				//new Edge(CRDJBH,3.6)
        		};
        		
        		BFC.adjacencies = new Edge[]{
        		
        				new Edge(DNTWRP,1.1)
        		};
        	
        		
        		CHATHM.adjacencies = new Edge[]{
        				
        				new Edge(MCCCRD, 0.2),
        				new Edge(MCCDIK,0.2)
        				
        		};
        	
        		
        		MCCDIK.adjacencies = new Edge[]{
        				
        				new Edge(CHATHM, 0.2),
        				new Edge(MCCMND, 0.3),
        				new Edge(DIKFRRD,0.7)
        				
        		};
        		
        		DIKFRRD.adjacencies = new Edge[]{
        				
        				new Edge(FRRDCRD, 0.4),
        				new Edge(MNDFRD, 0.4),
        				new Edge(MCCDIK,0.7)
        				
        		};
        		
        		MCCCRD.adjacencies = new Edge[]{
        				
        				new Edge(CHATHM, 0.2),
        				new Edge(CMPCRD, 0.7),
        				new Edge(FRRDCRD,0.6)
        				
        		};
        		
        		
        		CMPCRD.adjacencies = new Edge[]{
        				new Edge(MCCCRD, 0.7),
        				new Edge(WWCMP, 0.7)
        		};
        		
        		WWCMP.adjacencies = new Edge[] {			
        				new Edge(UTDWW, 0.5),
        				new Edge(CMPCRD, 0.7)
        				
        		};

        		WWWPK.adjacencies = new Edge[]{
        				
        				new Edge(WPKCRD, 1.3),
        				new Edge(WWFRRD,0.8)
        		};
        		
        		WPKCRD.adjacencies = new Edge[]{
        				
        				new Edge(MCP, 0.7),
        				//new Edge(FRRDCRD, 0.9),
        				new Edge(CRDJBH, 0.4),
        				new Edge(WWWPK,1.3),
        				new Edge(OHIOAID, 1.3)
        		};
        		

        		MCP.adjacencies = new Edge[] {			
        				//new Edge(FRRDCRD, 1.6),
        				//new Edge(CMPCRD, 2.9),
        				new Edge(CRDWSP, 2.5),
        				new Edge(WPKCRD, 0.7)
        		};
        		
        		
        		HHB.adjacencies = new Edge[] {			
        				
        				new Edge(OHIOAID, 0.3)
        		};
        		
        		OHIOAID.adjacencies = new Edge[] {			
        				
        				new Edge(HILOHIO, 0.5),
        				new Edge(HHB, 0.3),
        				new Edge(WPKCRD, 1.3)
        		};
        		
        		
        		HILFRD.adjacencies = new Edge[] {			
        				
        				new Edge(HILOHIO, 0.8),
        				new Edge(MCCHIL, 0.7),
        				new Edge(MNDFRD, 0.2)
        		};
        		
        		HILOHIO.adjacencies = new Edge[] {			
        				
        				new Edge(HILFRD, 0.8),
        				new Edge(OHIOAID, 0.5)
        		};
        		
        		MNDFRD.adjacencies = new Edge[] {			
        				
        				new Edge(HILFRD, 0.2),
        				new Edge(DIKFRRD, 0.4),
        				new Edge(MCCMND, 0.7)
        		};
        		
        		MCCMND.adjacencies = new Edge[] {			
        				
        				new Edge(MCCHIL, 0.3),
        				new Edge(MCCDIK, 0.3),
        				new Edge(MNDFRD, 0.7)
        		};
        		
        		MCCHIL.adjacencies = new Edge[] {			
        				
        				new Edge(HILFRD, 0.7),
        				new Edge(MCCMND, 0.3)
        		};
        		
        		
			AstarSearch(n1, n13);
	
			List<Node> path = printPath(n13);
	
			System.out.println("Path: " + path);
 
        		
        		AstarSearch(UTD, MCP);
        		
    			List<Node> path = printPath(MCP);
    	
    			System.out.println("Path: " + path);
     
        }
 
        public static List<Node> printPath(Node target){
                List<Node> path = new ArrayList<Node>();
        
        for(Node node = target; node!=null; node = node.parentNode){
            path.add(node);
        }
 
        Collections.reverse(path);
 
        return path;
        }
 
        public static void AstarSearch(Node source, Node goal){
 
                Set<Node> explored = new HashSet<Node>();
 
                PriorityQueue<Node> queue = new PriorityQueue<Node>(20, 
                        new Comparator<Node>(){
                                 //override compare method
                 public int compare(Node i, Node j){
                    if(i.f_value > j.f_value){
                        return 1;
                    }
 
                    else if (i.f_value < j.f_value){
                        return -1;
                    }
 
                    else{
                        return 0;
                    }
                 }
 
                        }
                        );
 
                //cost from start
                source.g_value = 0;
 
                queue.add(source);
 
                boolean found = false;
 
                while((!queue.isEmpty())&&(!found)){
 
                        //the node in having the lowest f_score value
                        Node current = queue.poll();
 
                        explored.add(current);
 
                        //goal found
                        if(current.connectingPlace.equals(goal.connectingPlace)){
                                found = true;
                        }
 
                        //check every child of current node
                        for(Edge e : current.adjacencies){
                                Node child = e.target;
                                double cost = e.cost;
                                double temp_g_scores = current.g_value + cost;
                                double temp_f_scores = temp_g_scores + child.h_value;
 
 
                                if child node has been evaluated and 
                                the newer f_score is higher, skip
                                
                                if((explored.contains(child)) && 
                                        (temp_f_scores >= child.f_value)){
                                        continue;
                                }
 
                                else if child node is not in queue or 
                                newer f_score is lower
                                
                                else if((!queue.contains(child)) || 
                                        (temp_f_scores < child.f_value)){
 
                                        child.parentNode = current;
                                        child.g_value = temp_g_scores;
                                        child.f_value = temp_f_scores;
 
                                        if(queue.contains(child)){
                                                queue.remove(child);
                                        }
 
                                        queue.add(child);
 
                                }
 
                        }
 
                }
 
        }
        */
}
 
