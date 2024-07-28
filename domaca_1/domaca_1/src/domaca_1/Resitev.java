package domaca_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;

public class Resitev {
  
	public static void resi(String vhodnaDatoteka, String izhodnaDatoteka, String izhodnaZaNjega) throws Exception {
			File inputFile = new File(vhodnaDatoteka);
			File outputFile = new File(izhodnaDatoteka);
		
			// read h, v, p
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			Scanner s = new Scanner(inputFile);
			Scanner sc = new Scanner(izhodnaZaNjega);
			int h = s.nextInt();
			int v = s.nextInt();
			int p = s.nextInt();
			
			// ************MAKING ARRAY OF HOUSE PETS NUMBERS********************
			
	        // initial array of slices
			ArrayList<ArrayList<Integer>> slices = new ArrayList<ArrayList<Integer>>();
	  
	        // print the original array
	        System.out.println(slices);
	        
	        // add first house
	        ArrayList<Integer> house_pets_array = new ArrayList<Integer>();
	        house_pets_array.add(0);
	        house_pets_array.add(0);
	        slices.add(house_pets_array);
	        
			// read V queries
			int prev_house = 0;
			int prev = 0;
			
			
			for (int i=0; i<v; i++) {
				if(s.hasNext()) {
					
				String letter = s.next();
				int house = s.nextInt();
				int pets = s.nextInt();
				//System.out.println(letter);
				//System.out.println(house);
				//System.out.println(pets);
				
				if (house > prev_house) {
					
					int pets_real = pets + prev;
					
					if (i==0) {
						ArrayList<Integer> house_pets_num = new ArrayList<Integer>();
						  
				        // add previous number of pets to all houses before this one
				        house_pets_num.add(house);
				        house_pets_num.add(pets);
				        
						slices.add(house_pets_num);
						
					}
					
					for (int k=prev_house+1; k < house; k++) {
				        // tuple (house, pet) to be added to slices list defined
				        ArrayList<Integer> house_pets_num = new ArrayList<Integer>();
				  
				        // add previous number of pets to all houses before this one
				        house_pets_num.add(k);
				        house_pets_num.add(prev);
				        
						slices.add(house_pets_num);
					}
					
					// add the new number of pets to this house, plus how many pets in houses before 
					ArrayList<Integer> house_pets_num = new ArrayList<Integer>();
					  
			        // call the method to add x in 
			        house_pets_num.add(house);
			        house_pets_num.add(pets_real);
			        
					slices.add(house_pets_num);
					
					// change prevs
					prev = pets_real;
					prev_house = house;
					
				};
		        
		        
		        
				}
			};
			
			// ************PROCESSING QUERIES***************
			
			for (int j=0; j<p;j++) {
				if (s.hasNext()) {
				String letter = s.next();
				int house_from = s.nextInt();
				int house_to = s.nextInt();
				Integer pets_num = 0;
				
				int pets_before = slices.get(house_from).get(1);
				int pets_until = slices.get(house_to).get(1);
				
				
				if (house_to != h & (slices.get(house_to).get(1) != slices.get(house_to+1).get(1))) {
					pets_num = slices.get(house_to+1).get(1)-pets_before;
					//System.out.println(pets_num);
					
				}
				else if (house_from != house_to) {
					pets_num = pets_until - pets_before;
					//System.out.println(pets_num);
					
				}
				else {
					pets_num = pets_until;
					//System.out.println(pets_num);
					
				}
				if (j==98506) {
					//System.out.println(pets_num.toString());
					
				}
				String pets_string = pets_num.toString();
			    writer.write(pets_string);
			    writer.write("\n");
			    
			   
			};
			
			
			};
			
		
	}

}
