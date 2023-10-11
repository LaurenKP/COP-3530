/**
 * This class is responsible for taking the array of objects and manipulating the variables.
 * The country constructor assigns the private variables from the class and makes the = to the constructor 
 * 
 * @author<lolo>
 * @version<09/09/23>
 *
 */



public class Country {

    private String name;
    private String capitol;
    private long population;
    private double GDP;
    private double area;
    private double happinessIndex;

    /**
     * Constructor for the Country class.
     *
     * @param name Name of the country.
     * @param capitol Capitol of the country.
     * @param population Population of the country.
     * @param GDP GDP of the country.
     * @param area Area of the country.
     * @param happinessIndex Happiness index of the country.
     */
    public Country(String name, String capitol, long population, double GDP, double area, double happinessIndex) {
        this.name = name;
        this.capitol = capitol;
        this.population = population;
        this.GDP = GDP;
        this.area = area;
        this.happinessIndex = happinessIndex;
    }

    // Getter and Setter methods for each field
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCapitol() { return capitol; }
    public void setCapitol(String capitol) { this.capitol = capitol; }

    public long getPopulation() { return population; }
    public void setPopulation(long population) { this.population = population; }

    public double getGDP() { return GDP; }
    public void setGDP(double GDP) { this.GDP = GDP; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public double getHappinessIndex() { return happinessIndex; }
    public void setHappinessIndex(double happinessIndex) { this.happinessIndex = happinessIndex; }

    @Override
    public String toString() {
        return "Country: " + name + ", Capitol: " + capitol + ", Population: " + population +
               ", GDP: " + GDP + ", Area: " + area + ", Happiness Index: " + happinessIndex;
    }
}
