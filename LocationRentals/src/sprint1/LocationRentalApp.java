package sprint1;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
public class LocationRentalApp {
    /**
     * 
     */
    // sending new objects to array list
    static ArrayList<LocationRental> lrentals = new ArrayList<LocationRental>();
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("********WELCOME to RentalPrize********");
        System.out.println("______________________________________");
        // Here we should create or load a text file containing the arraylist holding
        // locationRental
        // calling display option menu
        try {
            makeFile();
            readFile();
        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            displayMenu();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated catch block
    }
    // prompt user input
    private static LocationRental getUserInput() {
        System.out.println("You are now entering the details for a location name.");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter rental location name");
        String locationName = sc.nextLine();
        System.out.println("Enter rental location ID");
        int storeId = Integer.parseInt(sc.nextLine());
        System.out.println("Enter rental location zipcode");
        int zipCode = Integer.parseInt(sc.nextLine());
        System.out.println("Enter daily rental rate");
        double rentalRate = Double.parseDouble(sc.nextLine());
        System.out.println("Enter total number of vehicles");
        int totalCar = Integer.parseInt(sc.nextLine());
        System.out.println("Enter number of vehicles that are rented out right now");
        int rentedVechicles = Integer.parseInt(sc.nextLine());
        // setting the user input to the constructor to create new object
        LocationRental loRent = new LocationRental(locationName, storeId, zipCode, rentalRate, totalCar,
                rentedVechicles);
        return loRent;
    }
    // method to display option menu
    public static void displayMenu() throws IOException {
        System.out.println("\nRENTAL LOCATION MENU");
        System.out.println("1. Enter new location");
        System.out.println("2. List all locations and rates");
        System.out.println("3. Find location by zipcode");
        System.out.println("4. View location details");
        System.out.println("5. View location rental rates");
        System.out.println("6. View number of available vechicles");
        System.out.println("7. View all location's revenue");
        System.out.println("8. Delete a location");
        System.out.println("9. Exit \n");
        System.out.println("Type the number that corresponds to what you want to do.");
        userMenuSelection();
    }
    /*
     * A method that takes action according to what the user selected in the main
     * menu.
     */
    public static void userMenuSelection() throws IOException {
        Scanner sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        // if the user wants to add a location
        if (userChoice == 1) {
            LocationRental lr = getUserInput();
            lrentals.add(lr);
        }
        else if (userChoice == 2) {
            printLocationsAndRates();
        }
        else if (userChoice == 3) {
            getZipLocations(); // should find location by zip code.
        }
        else if (userChoice == 4) {
            getLocationData(4);
        }
        else if (userChoice == 5) {
            getLocationData(5);
        }
        else if (userChoice == 6) {
            // show user the number of available vehicles for all the locations
            System.out.println("The number of available vehicles is " + availableVechicles());
        }
        else if (userChoice == 7) {
            // Show the total revenue for all of the locations put together
            System.out.println("Total Revenue: " + NumberFormat.getCurrencyInstance().format(calculateTotalRevenue()));
        }
    	else if (userChoice == 8) {
    		getLocationData(8);
    	}
        else if (userChoice == 9) {
            System.out.println("Thanks for using our program");
            // Here we should save the arraylist into a text file.
            writeFile();
            System.exit(1);
        }
        else {
            System.out.println("That is not a valid command.");
        }
        displayMenu();
    }
 // method to search for location and output all data for that location
    public static void getLocationData(int userInput) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter location name:");
        String locationNamez = sc.nextLine();
        boolean locationFound = false;
        for (int i = 0; i < lrentals.size(); i++) {
            if (locationNamez.equalsIgnoreCase(lrentals.get(i).locationName)) {
                if (userInput == 4) {
                    System.out.println(lrentals.get(i).toString());
                    locationFound = true;
                }
                if (userInput == 5) {
                    System.out.println("Rental Rate for this location: "
                            + NumberFormat.getCurrencyInstance().format(lrentals.get(i).rentalRate));
                    locationFound = true;
                }
                if(userInput == 8) {
                	deleteLocation(lrentals.get(i));
                	locationFound = true;
                }
            }
        }
        if (!locationFound) {
            System.out.println("We could not find this location.");
        }
    }
    // method to list all rental locations and their rates
    public static void printLocationsAndRates() {
        for (int i = 0; i < lrentals.size(); i++) {
            System.out.println("Location Name: " + lrentals.get(i).locationName);
            System.out.println(
                    "Location Daily Rate: " + NumberFormat.getCurrencyInstance().format(lrentals.get(i).rentalRate));
            System.out.println();
        }
    }
    // method to find total vehicles available
    public static int availableVechicles() {
        int sum = 0;
        for (LocationRental rent : lrentals) {
            sum += rent.totalNumberCars - rent.rentedCars;
        }
        return sum;
    }
    // method to return all locations within a zipcode
    public static void getZipLocations() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter rental location zipcode");
        int i = sc.nextInt();
        System.out.println("The locations within zipcode " + i + " are: ");
        boolean zipFound = false;
        for (LocationRental zipLoc : lrentals) {
            if (i == zipLoc.zipCode) {
                System.out.println(zipLoc.locationName + "  ");
                zipFound = true;
            }
        }
        if (!zipFound) {
            System.out.println("There is no locations within this zip code.");
        }
    }
    // method to find total vehicles available
    // method to find total vehicles available
    public static double calculateTotalRevenue() {
        double sum = 0;
        for (LocationRental rent : lrentals) {
            System.out.println("Location Name: " + rent.locationName + "\n" + "Daily Revenue: "
                    + NumberFormat.getCurrencyInstance().format(rent.calculateDailyRevenue()) + "\n");
            sum += rent.calculateDailyRevenue();
        }
        return sum;
    }
    public static void readFile() throws IOException {
        Scanner sc = new Scanner(new File("RentalPrize.txt")).useDelimiter("\r");
        String locationName;
        int locationID;
        int zipCode;
        double rentalRate;
        int totalNumberCars;
        int rentedCars;
        while (sc.hasNext()) {
            locationName = sc.nextLine();
            locationID = Integer.parseInt(sc.nextLine());
            zipCode = Integer.parseInt(sc.nextLine());
            rentalRate = Double.parseDouble(sc.nextLine());
            totalNumberCars = Integer.parseInt(sc.nextLine());
            rentedCars = Integer.parseInt(sc.nextLine());
            LocationRental lr = new LocationRental(locationName, locationID, zipCode, rentalRate, totalNumberCars,
                    rentedCars);
            lrentals.add(lr);
            continue;
        }
    }
    public static void writeFile() {
        try {
            FileWriter writeData = new FileWriter("RentalPrize.txt");
            BufferedWriter br = new BufferedWriter(writeData);
            for (int i = 0; i < lrentals.size(); i++) {
                br.write(lrentals.get(i).locationName + "\r");
                br.write(lrentals.get(i).locationId + "\r");
                br.write(lrentals.get(i).zipCode + "\r");
                br.write(lrentals.get(i).rentalRate + "\r");
                br.write(lrentals.get(i).totalNumberCars + "\r");
                br.write(lrentals.get(i).rentedCars + "\r");
                br.flush();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void makeFile() throws IOException {
        File rentalPrize = new File("RentalPrize.txt");
        if (rentalPrize.exists()) {
        }
        else {
            rentalPrize.createNewFile();
        }
    }
    public static void deleteLocation(LocationRental lr) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Are you sure you would like to delete this location? Y/N");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            lrentals.remove(lr);
            System.out.println("The location " + lr.locationName + " was removed");
        }
    } 
}