package dn_2_project;

import java.util.ArrayList;
import java.util.Comparator;

public class User implements Comparable<User>, Comparator<User> {
	String username;
	long birthday;
	int num_watched;
	ArrayList<int[]> intervals_watched;
	
	public User(String username, long birthday, int num_watched, ArrayList<int[]> intervals_watched) {
    	this.username = username;
    	this.birthday = birthday;
    	this.num_watched = num_watched;
    	this.intervals_watched=intervals_watched;
    }
	
	@Override
	public int compareTo(User u)
	  {
	    return Long.compare(this.birthday, u.birthday);
	  }
	
	@Override 
	public int compare(User u1, User u2)
	  {
	    return Long.compare(u1.birthday, u2.birthday);
	  }
}
