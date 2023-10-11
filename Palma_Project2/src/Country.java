
/**
 * Class to represent a country with its various attributes.
 *
 * @author <Lauren Palma>
 * @version <2023-10-06>
 */
public class Country {
    private String name;
    private String capitol;
    private long population;
    private double gdp;
    private double area;
    private double happinessIndex;

    public Country(String name, String capitol, long population, double gdp, double area, double happinessIndex) {
        this.name = name;
        this.capitol = capitol;
        this.population = population;
        this.gdp = gdp;
        this.area = area;
        this.happinessIndex = happinessIndex;
    }

    public double getHappinessIndex() {
    return happinessIndex;
    }

    public double getGDPPC() {
        return gdp / population;
    }

    public double getAPC() {
        return area / population;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.3f %.6f %.3f", name, capitol, getGDPPC(), getAPC(), happinessIndex);
    }
}
