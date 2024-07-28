package dn_2_project;

import java.util.ArrayList;
import java.util.Collections;

public class IntervalTree {
	Node root=null;
	
	public IntervalTree(Node root) {
		this.root = root;
	}
	
	// function to add a node to a tree
	public void addNode(Node new_node) {
		Node node  = this.root;
		while (node!= null) {
			if (new_node.interval[0] <= node.interval[0]) { // if it starts before
				if (node.left_child == null) {
					node.left_child = new_node;	
					break;
				}
				
				node = node.left_child;			
				
			}
			
			else {
				if (node.right_child==null) {
					
					node.right_child = new_node;
					break;
				}
				node = node.right_child;
				
			}
		}
	}
	
	public int maxOfSubtree(Node root_node, ArrayList<Integer> max_array) {
		
		if (max_array == null) {
			max_array = new ArrayList<Integer>();
			
		}
		
        if((root_node.max != null) && (root_node.left_child != null || root_node.right_child != null)) {
	            
	            if(root_node.left_child != null) {
	            	
	                maxOfSubtree(root_node.left_child, max_array);
	                max_array.add(root_node.left_child.max);
	                
	            }
	            
	            if(root_node.right_child != null) {
	                maxOfSubtree(root_node.right_child, max_array);
	                max_array.add(root_node.right_child.max);
	                
	            }
	            
	            max_array.add(root_node.interval[1]);
	            root_node.max = Collections.max(max_array);
	            return root_node.max;
	            
        }
	        else {
	            root_node.max = root_node.interval[1];
	            return root_node.max;
	        }
	}
	
	public void constructMax() {
        Node node = this.root;
        this.maxOfSubtree(node, null);
	}
	
	
	public ArrayList<Node> findOverlappingIntervals(Node query_node) {
		
        
        Node curr_node = this.root;
        ArrayList<Node> overlapping_intervals = new ArrayList();
        
        while(curr_node != null) {
        	
        	// check if current node is overlapping
            if (curr_node.isOverlapping(query_node)) {
                // System.out.println("Overlap found with: " + curr_node.toString());
                overlapping_intervals.add(curr_node);
            }
            	
	        
	        
	        if ((curr_node.left_child != null) && (curr_node.left_child.max >= query_node.interval[0])) { 
	        	// if subtree starting from current node contains an interval ending
	            // after the start of query interval, explore left child of curr_node
	        	
	        	curr_node = curr_node.left_child;
	        }
	        
	        else {
	            curr_node = curr_node.right_child;
	        }
          		
        }
        
        return overlapping_intervals;
	};
	
	

	
}
