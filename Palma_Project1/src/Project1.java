/**
 * This class takes the provided CSV file as input, and will print out a menu of options
 * that the user can select from
 * 
 * @author<laurenPalma>
 * @version<09/09/23>
 */


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Project1 {


		private static Country[] countries;
	    private static boolean isSortedByName = false;

	    public static void main(String[] args) {
	        System.out.println("COP3530 Project 1");
	        System.out.println("Student: Lauren Palma");
	        System.out.println("Array Searches and Sorts");

	        loadCountriesData();
	        displayMenu();
	    }
	    
	    private static void loadCountriesData() {
	        Scanner scanner = new Scanner(System.in);
	        boolean fileLoaded = false;

	        while (!fileLoaded) {
	            System.out.print("Enter the file name: ");
	            String filename = scanner.nextLine();

	            try {
	                Scanner fileScanner = new Scanner(new File(filename));
	                fileScanner.nextLine(); // skipping the header

	                int count = 0;
	                while (fileScanner.hasNextLine()) {
	                    count++;
	                    fileScanner.nextLine();
	                }

	                countries = new Country[count];
	                fileScanner = new Scanner(new File(filename));
	                fileScanner.nextLine(); // skipping the header again

	                int index = 0;
	                while (fileScanner.hasNextLine()) {
	                    String line = fileScanner.nextLine();
	                    String[] data = line.split(",");
	                    countries[index] = new Country(data[0], data[1], Long.parseLong(data[2]),
	                                                   Double.parseDouble(data[3]), Double.parseDouble(data[4]),
	                                                   Double.parseDouble(data[5]));
	                    index++;
	                }
	                System.out.println("There were " + count + " records read.");
	                fileLoaded = true;  // Set to true so loop exits after successful loading

	            } catch (FileNotFoundException e) {
	                System.out.println("Error: File not found.");
	            }
	        }
	    }


	    /**
	     * Displays the main menu and processes user choices.
	     */
	    private static void displayMenu() {
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("1. Print a countries report");
	            System.out.println("2. Sort by Name");
	            System.out.println("3. Sort by Happiness Index");
	            System.out.println("4. Sort by GDP per capita");
	            System.out.println("5. Find and print a given country");
	            System.out.println("6. Print Kendall’s tau matrix");
	            System.out.println("7. Quit");

	            System.out.print("Enter your choice: ");
	            while (!scanner.hasNextInt()) {
	                System.out.println("Invalid choice! Enter 1-7.");
	                scanner.nextLine(); // Consume the incorrect input
	            }
	            choice = scanner.nextInt();
	            scanner.nextLine(); 
	            switch (choice) {
	                case 1:
	                    printCountriesReport();
	                    break;
	                case 2:
	                    insertionSortByName();
	                    System.out.println("Countries sorted by Name.");
	                    isSortedByName = true;
	                    break;
	                case 3:
	                    selectionSortByHappinessIndex();
	                    System.out.println("Countries sorted by Happiness Index.");
	                    break;
	                case 4:
	                    bubbleSortByGDPPerCapita();
	                    System.out.println("Countries sorted by GDP per capita.");
	                    break;
	                case 5:
	                    System.out.print("Enter country name: ");
	                    String countryName = scanner.next();
	                    findAndPrintCountry(countryName);
	                    break;
	                case 6:
	                    printKendallsTauMatrix();
	                    break;
	                case 7:
	                    System.out.println("Have a good day!");
	                    break;
	                default:
	                    System.out.println("Invalid choice! Enter 1-7.");
	                
	            }
	        } while (choice != 7);
	    }

	    /**
	     * Sorts the countries array by name using Insertion sort.
	     */
	    private static void insertionSortByName() {
	        for (int i = 1; i < countries.length; i++) {
	            Country key = countries[i];
	            int j = i - 1;

	            while (j >= 0 && countries[j].getName().compareTo(key.getName()) > 0) {
	                countries[j + 1] = countries[j];
	                j = j - 1;
	            }
	            countries[j + 1] = key;
	        }
	    }

	    /**
	     * Sorts the countries array by happiness index using Selection sort.
	     */
	    private static void selectionSortByHappinessIndex() {
	        for (int i = 0; i < countries.length - 1; i++) {
	            int minIdx = i;
	            for (int j = i + 1; j < countries.length; j++) {
	                if (countries[j].getHappinessIndex() < countries[minIdx].getHappinessIndex()) {
	                    minIdx = j;
	                }
	            }
	            Country temp = countries[minIdx];
	            countries[minIdx] = countries[i];
	            countries[i] = temp;
	        }
	        System.out.println("Countries sorted by Happiness Index.");
	    }

	    /**
	     * Sorts the countries array by GDP per capita using Bubble sort.
	     */
	    private static void bubbleSortByGDPPerCapita() {
	        for (int i = 0; i < countries.length - 1; i++) {
	            for (int j = 0; j < countries.length - i - 1; j++) {
	                double gdpPerCapitaJ = countries[j].getGDP() / countries[j].getPopulation();
	                double gdpPerCapitaJPlus1 = countries[j + 1].getGDP() / countries[j + 1].getPopulation();
	                if (gdpPerCapitaJ > gdpPerCapitaJPlus1) {
	                    Country temp = countries[j];
	                    countries[j] = countries[j + 1];
	                    countries[j + 1] = temp;
	                }
	            }
	        }
	        System.out.println("Countries sorted by GDP per capita.");
	    }

	    /**
	     * Finds and prints a country based on the given name.
	     * Uses binary search if countries are sorted by name; sequential search otherwise.
	     *
	     * @param name Name of the country to search for.
	     */
	    private static void findAndPrintCountry(String name) {
	        if (isSortedByName) {
	            System.out.println("Binary search is used");
	            int idx = binarySearch(name);
	            if (idx != -1) {
	                printCountryDetails(countries[idx]);
	            } else {
	                System.out.println("Error: country " + name + " not found");
	            }
	        } else {
	            System.out.println("Sequential search is used");
	            for (Country country : countries) {
	                if (country.getName().equalsIgnoreCase(name)) {
	                    printCountryDetails(country);
	                    return;
	                }
	            }
	            System.out.println("Error: country " + name + " not found");
	        }
	    }

	    /**
	     * Conducts a binary search to find a country by name.
	     *
	     * @param name Name of the country to search for.
	     * @return Index of the country in the array; -1 if not found.
	     */
	    private static int binarySearch(String name) {
	        int l = 0, r = countries.length - 1;
	        while (l <= r) {
	            int mid = l + (r - l) / 2;

	            int res = name.compareTo(countries[mid].getName());

	            if (res == 0) {
	                return mid;
	            }

	            if (res > 0) {
	                l = mid + 1;
	            } else {
	                r = mid - 1;
	            }
	        }
	        return -1;
	    }

	    /**
	     * Prints the details of a country.
	     *
	     * @param country Country object to print.
	     */
	    private static void printCountryDetails(Country country) {
	        System.out.println("Name: " + country.getName());
	        System.out.println("Capital: " + country.getCapitol());
	        System.out.printf("GDPPC: %.3f%n", country.getGDP() / country.getPopulation());
	        System.out.printf("APC: %.6f%n", country.getArea() / country.getPopulation());
	        System.out.printf("Happiness: %.3f%n", country.getHappinessIndex());
	    }


	    /**
	     * Prints the countries report.
	     */
	    private static void printCountriesReport() {
	        System.out.println("Name           Capitol           GDPPC    APC         HappinessIndex");
	        System.out.println("-------------------------------------------------------------------------");
	        for (Country country : countries) {
	            System.out.printf("%-15s %-15s %-9.3f %-11.6f %-13.3f%n", 
	                              country.getName(), 
	                              country.getCapitol(),
	                              country.getGDP() / country.getPopulation(),
	                              country.getArea() / country.getPopulation(),
	                              country.getHappinessIndex());
	        }
	    }

	    /**
	     * Computes and prints Kendall's tau matrix.
	     */
	    private static void printKendallsTauMatrix() {
	        double tauGDPHappiness = computeKendallsTau(countries, "GDP", "Happiness");
	        double tauAreaHappiness = computeKendallsTau(countries, "Area", "Happiness");

	        System.out.println("---------------------------------------------");
	        System.out.println("|                  | GDPPC  | AreaPC  |");
	        System.out.println("---------------------------------------------");
	        System.out.printf("| Happiness Index | %.4f | %.4f |%n", tauGDPHappiness, tauAreaHappiness);
	        System.out.println("---------------------------------------------");
	    }



	    /**
	     * Computes Kendall's tau for two rankings.
	     *
	     * @param countries Array of Country objects.
	     * @param ranking1  First ranking criteria (GDP or Area).
	     * @param ranking2  Second ranking criteria (Happiness).
	     * @return Computed Kendall's tau value.
	     */
	    private static double computeKendallsTau(Country[] countries, String ranking1, String ranking2) {
	        int n = countries.length;
	        int concordantPairs = 0;
	        int discordantPairs = 0;

	        for (int i = 0; i < n - 1; i++) {
	            for (int j = i + 1; j < n; j++) {
	                double iRanking1 = (ranking1.equals("GDP") ? countries[i].getGDP() : countries[i].getArea());
	                double jRanking1 = (ranking1.equals("GDP") ? countries[j].getGDP() : countries[j].getArea());
	                
	                double iRanking2 = countries[i].getHappinessIndex();
	                double jRanking2 = countries[j].getHappinessIndex();

	                if ((iRanking1 - jRanking1) * (iRanking2 - jRanking2) > 0) {
	                    concordantPairs++;
	                } else if ((iRanking1 - jRanking1) * (iRanking2 - jRanking2) < 0) {
	                    discordantPairs++;
	                }
	            }
	        }
	        return (double) (concordantPairs - discordantPairs) / (n * (n - 1) / 2);
	    }
	}