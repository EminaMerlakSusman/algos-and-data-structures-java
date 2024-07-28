package dn_2_project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;



class Pair
{
	User key;
	Long value;
	
	public Pair(User key, Long value)
	{
		this.key = key;
		this.value = value;
	}
	public User getKey()
	{
		return key;
	}
	public void setKey(User key)
	{
		this.key = key;
	}
	public Long getValue()
	{
		return value;
	}
	public void setValue(Long value)
	{
		this.value = value;
	}
}


public class Resitev {
	
    public static User[] fiveClosestNeighbours(User[] users_normal_list, long query_user, int users_added, boolean[] delete_list) {
    	
        if (query_user <= users_normal_list[0].birthday) {
        	// **** Tukaj ne pogleda za izbrisane elemente
        	User[] sublist = Arrays.copyOfRange(users_normal_list, 0, 5);
        	
            return sublist;
            
        } else if (users_normal_list[users_added - 1].birthday <= query_user) {
        	// **** Tukaj ne pogleda za izbrisane elemente
        	User[] sublist = Arrays.copyOfRange(users_normal_list, users_added - 5, users_added);
            return sublist;
            
        } else {
        	int[] dummy_interval = {0, 0};
        	ArrayList<int[]> dummy_intervals = new ArrayList<int[]>();
        	dummy_intervals.add(dummy_interval);
        	User dummy_user = new User("blabla", query_user, 0, dummy_intervals);
        	
        	// User[] actual_users = Arrays.copyOfRange(users_normal_list, 0, users_added-1);
            int index = Arrays.binarySearch(users_normal_list, 0, users_added, dummy_user);
            if (index < 0)
                index = -index - 1;
            
            User[] closest_neighbours = new User[5];
            int found = 0;
            
         // find five undeleted neighbours to the left, and five undeleted neighbours to the right.
            // sort this result to get the first five closest neighbours
//            ArrayList<User> cadidates = new ArrayList<User>();
//       	 		long first = users_normal_list[index].birthday;
//       	 
//              
       // }
            int low = Math.max(0, index - 1), high = Math.min(users_added - 1, index); // index - 5 - 1; users_added - 1, index + 5 - 1
            

            while (found < 5) {
            	// find first undeleted element to the right of index
            	// and first undeleted element to the left of index
            	while(delete_list[users_normal_list[low].hashCode()/100] == true) { // check if user on low index was deleted
            		if (low - 1 > 0) {  
            		low--;
            		}
            		
            	}
            	while (delete_list[users_normal_list[high].hashCode()/100] == true) {
            		if (high < users_added-1) {
            			high++;
            		}
            		
            	}
            	long diff1 = query_user - users_normal_list[low].birthday;
            	long diff2 = users_normal_list[high].birthday - query_user;
            	
            	if (diff2 >= diff1) {
            		closest_neighbours[found] = users_normal_list[low];
            	}
            	else {
            		closest_neighbours[found] = users_normal_list[high];
            	}
            	
            	found++;
            	if (low -1 > 0 && diff1 < diff2) { 
            		low--;
            	} else if (low - 1 == 0) { // if low is too low, add elements from end
            		
            		int added = 0;
            		int start = index + 5 + 1;
            		while (added < found - users_added) { // fill up found list
            			if (delete_list[users_normal_list[low].hashCode()/100] != true) { // if not deleted
            				closest_neighbours[found] = users_normal_list[start];
            				added++;
            				found++;
            		}
        			
            		start++;
        			
            		
            		}
            	}
            	if (high+1 < users_added && diff2 < diff1) {
            		high++;
            	} else if (high + 1 == users_added) { // if high is too high, add elements from before
            		
            		int added = 0;
            		int start = index - 5 - 1;
            		while (added < found - users_added) {
            			if (delete_list[users_normal_list[low].hashCode()/100] != true) { // if not deleted
            				closest_neighbours[found] = users_normal_list[start];
            				added++;
            				found++;
            		}
        			
            		start--;
        			
            		
            		}
            	}
            	 
            	}
    	
            return closest_neighbours;
    	
          }
        
     }
	
	
	private static int binarySearch(User[] users_normal_list, long query_user, int low, int high) {
		
		int mid = (high - low) / 2;
		
		
		long val = users_normal_list[mid].birthday - query_user;
		if (val == 0) {
			return mid;
			}
		
		else if (val <= 0) { // if this elt is smaller that query elt, do right half
			User[] right_half = Arrays.copyOfRange(users_normal_list, mid, high);
			
			
			binarySearch(right_half, query_user, mid, high);
		}
		
		else {
			User[]left_half = Arrays.copyOfRange(users_normal_list, 0, mid);
			binarySearch(left_half, query_user, 0, mid);
			
		}
			
		
		return 0;
	}

	public static void resi(String vhodnaDatoteka, String izhodnaDatoteka) throws Exception {
		
		
		File inputFile = new File(vhodnaDatoteka);
		File outputFile = new File(izhodnaDatoteka);
	
		// read D, P, I
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		Scanner s = new Scanner(inputFile);
		
		int D = s.nextInt();
		int P = s.nextInt();
		int I = s.nextInt();
		
		User[] users_normal_list = new User[250000];
		User[] database = new User[100000000];
		boolean[] delete_list = new boolean[100000000];
		ArrayList<Integer> birthdays = new ArrayList<Integer>();
		ArrayList<User> users = new ArrayList<User>(250000);
		
		int count=0;
		int users_added=0;
		for (int i=0; i<D+I+P; i++) {
			String type = s.next();
			
			if (type.equals("dodaj")) {
				String username = s.next();
				long birthday = s.nextLong();
				
				int num_watched = s.nextInt();
				ArrayList<int[]> intervals_watched = new ArrayList<int[]>();
				for (int j=0; j<num_watched; j++) {
					int st = s.nextInt();
					int end = s.nextInt();
					int[] interval = {st, end};
					intervals_watched.add(interval);
				}
				
				User user = new User(username, birthday, num_watched, intervals_watched);
				// int insertion_index = Math.abs((user.username).hashCode() / 100);
				
				//insert user in a way that keeps the list sorted
				if (users_added == 0) {
					users_normal_list[0] = user;
				}
				
				else {
				int k;
				for (k = users_added - 1; k >= 0 && users_normal_list[k].birthday > user.birthday; k--) {
					
						users_normal_list[k+1] = users_normal_list[k]; // shift all younger users left
						// users.set(k+1, users_normal_list[k]);

				};
				users_normal_list[k+1] = user; // finally, put user on this index
				}
				users_added+=1;
				
			}
			
			else if(type.equals("odstrani")) {
				String username = s.next();
				long birthday = s.nextLong();
				int hash_value = Math.abs((username).hashCode() / 100);
			
				if (delete_list[hash_value] == true) {
					count+=1;
				}
				delete_list[hash_value] = true;
				
			}
			
			else if(type.equals("poizvedba")) {
				long birthday = s.nextLong();
				
				
				User[] neighbours = fiveClosestNeighbours(users_normal_list, birthday, users_added, delete_list);
				
				if (i==250003) {
					System.out.print("closest: ");
					for (int q=0; q < 5; q++) {
						System.out.print(Math.abs(neighbours[q].birthday - birthday));
						System.out.print(", ");
					}
					
					int[] dummy_interval = {0, 0};
		        	ArrayList<int[]> dummy_intervals = new ArrayList<int[]>();
		        	dummy_intervals.add(dummy_interval);
		        	User dummy_user = new User("blabla", birthday, 0, dummy_intervals);
		        	
					int index = Arrays.binarySearch(users_normal_list, dummy_user);
					System.out.println(users_normal_list[-index - 1].birthday);
					User[] slice = Arrays.copyOfRange(users_normal_list, -index - 1 - 6, -index - 1 + 6);
					System.out.println();
					System.out.print("slice: ");
					long[] lstg = new long[12];
					for (int v=0; v<slice.length; v++) {
						lstg[v] = Math.abs(slice[v].birthday - birthday);
						// System.out.print(", ");
					}
					Arrays.sort(lstg);
					System.out.print(Arrays.toString(lstg));
					
					
				}
				
				
				
				// making interval tree of neighbours
				//setting first neighbour as root node
				ArrayList<int[]> intervals_watched = neighbours[0].intervals_watched;
				int[] root_interval = neighbours[0].intervals_watched.get(0);
				Node root_node = new Node(root_interval); // first interval watched by first neighbour
				
				ArrayList<Node> all_nodes = new ArrayList<Node>(50);
				
				IntervalTree interval_tree = new IntervalTree(root_node);
				
				for (int g = 0; g < 5; g++) {
					
					User neighbour = neighbours[g];
					
					if (g==0) { // for each neighbour, go throug their intervals and add them to tree
						
						for (int f=1; f<neighbour.num_watched;f++) {
							Node interval = new Node(neighbour.intervals_watched.get(f));
							interval_tree.addNode(interval);
							interval_tree.maxOfSubtree(interval, null);
							all_nodes.add(interval);
						}
					}
					
					else { // for each neighbour, go throug their intervals and add them to tree
						
						for (int f=0; f<neighbour.num_watched;f++) {
							Node interval = new Node(neighbour.intervals_watched.get(f));
							interval_tree.addNode(interval);
							interval_tree.maxOfSubtree(interval, null);
							all_nodes.add(interval);
						}
					}
				}
				
				ArrayList<ArrayList<Node>> intersections = new ArrayList<ArrayList<Node>>();
				
				interval_tree.constructMax();
				
				// for each neighbour, find overlapping intervals in tree
				for (int d=0; d<all_nodes.size(); d++) {
					Node query_node = all_nodes.get(d);
					intersections.add(interval_tree.findOverlappingIntervals(query_node));
				}
				
				
				int max = 0;
				
				// find longest intersection
				for (int m=0; m<intersections.size(); m++) {
					ArrayList<Node> nodes = intersections.get(m);
					if (nodes.size() > max) {
						max = nodes.size();
					}
					}
				//System.out.println(max);
				int earliest = 168*3600;
				// find earliest index of intersections with length max
				for (int x=0; x<intersections.size(); x++) {
					ArrayList<Node> nodes_list = intersections.get(x);
					
					if (nodes_list.size() <= max) { // get node in list with earliest start ** changed to latest
						// max = nodes_list.size();
						int latest = 0;
						for (int k = 0; k<nodes_list.size(); k++) {
							// System.out.println(nodes_list.get(k));
						}
						for (int y=0; y < nodes_list.size(); y++) {
							Node nd = nodes_list.get(y);
							if (nd.interval[0] > latest) {
								latest = nd.interval[0];
								// System.out.print(nd.interval[0] + " " + nd.interval[1] +"\n");
							};
							
							
						};
						// System.out.println();
						
						if (latest < earliest && nodes_list.size() > max) {
							earliest=latest;
						}
						
						if (nodes_list.size() == max) {
							if (latest < earliest) {
								earliest=latest;
							}
						}
						
						//else {earliest=min;}
					}
					
					
				};
				writer.write(String.valueOf(earliest));
				writer.newLine();
				// System.out.println(earliest);
			}
			
			
			
		}
		//System.out.println("count is " + count);
			
		s.close();
		writer.close();

	}
	
}

