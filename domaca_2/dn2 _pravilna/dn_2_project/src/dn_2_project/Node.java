package dn_2_project;

import java.util.Arrays;

public class Node {
	
	int[] interval;
	Node left_child;
	Node right_child;
	Integer max;
    
    public Node(int[] interval) {
    	this.interval = interval;
    	this.left_child = null;
    	this.right_child = null;
    	this.max = 0;
    }
    
    public boolean isOverlapping(Node query_node) {
    	
        return (this.interval[0] <= query_node.interval[1]) && (this.interval[1] >= query_node.interval[0]);
    }
    
    @Override
    public String toString() {
      return "Node<" + Arrays.toString(this.interval) + ", " + this.max.toString() + ">"; // + "left: " + Arrays.toString(this.left_child.interval) + "right: " + Arrays.toString(this.right_child.interval);
    }
}