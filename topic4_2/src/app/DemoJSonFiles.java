package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoJSonFiles {
	
	private static void saveToFile(String FileName, Car car, boolean append) {
		PrintWriter pw;
		try {
			// Create a file File to write
			File file = new File(FileName);
			FileWriter fw = new FileWriter(file, append);
			pw = new PrintWriter(fw);
			
			// Write Car as JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(car);
			pw.println(json);
			
			// Cleanup
			/*
			 * The issue with the above code is that we have an inconsistent Variable Name
			 * for 'FileName' in the method's body. I changed it to be the same as the method's
			 * parameter 'FileName'. Also there is no import statement for ObjectMapper.
			 */
			pw.close();
		}
		catch(IOException e){
			//Print Exception
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Car> readFromFile(String FileName){
		ArrayList<Car> cars = new ArrayList<Car>();
		try {
			
			// Open file File to read
			File file = new File(FileName);
			Scanner scnr = new Scanner(file);
			
			// Create List of Cars by reading JSON file
			while(scnr.hasNext()) {
				String json = scnr.nextLine();
				ObjectMapper objectMapper = new ObjectMapper();
				Car car = objectMapper.readValue(json, Car.class);
				cars.add(car);
			}
			
			// cleanup
			scnr.close();		
			
		}
		catch(IOException e){
			// Print exception
			e.printStackTrace();
		}
		
		return cars;
	}
	
	public static void main(String[] args) throws IOException {
		// Creating cars using an array
		Car[] cars = new Car[5];
		cars[0] = new Car(2018, "Ford", "Mustang", 0, 1.5d);
		cars[1] = new Car(2018, "Chevy", "Blazer", 1000, 1.5d);
		cars[2] = new Car(2019, "Toyota", "Camery", 0, 1.5d);
		cars[3] = new Car(2018, "Toyota", "Avalon", 0, 2.5d);
		cars[4] = new Car(2010, "GMC", "Silverado", 0, 3.5d);
		
		for (int i = 0; i < 4; i++) {
			// Write Cars to a file as JSON
			saveToFile("out.json", cars[i], true);
		}
		
		// Read the Cars from the file and print out
		ArrayList<Car> carsList = readFromFile("out.json");
		for (Car car: carsList) {
			String text = Integer.toString(car.getYear()) + "," + 
													car.getMake() + "," +
													car.getModel() + "," +
													Integer.toString(car.getOdometer()) + "," +
													Double.toString(car.getEngineLiters());
			
			System.out.println(text);
																
		}
	}

}
