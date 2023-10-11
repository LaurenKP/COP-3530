
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main driver class for the project.
 *
 *  @author <Lauren Palma>
 * @version <2023-10-06>
 */
public class Project2 {
    private Stack stack;
    private PriorityQ priorityQ;

    public Project2() {
        stack = new Stack(128);  
        priorityQ = new PriorityQ(128); 
    }

    public void readFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Country country = new Country(data[0], data[1], Long.parseLong(data[2]), 
                                              Double.parseDouble(data[3]), Double.parseDouble(data[4]), 
                                              Double.parseDouble(data[5]));
                stack.push(country);
                priorityQ.insert(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        readFromFile("Countries2.csv");  
        stack.printStack();
        priorityQ.printPriorityQ();
    }

    public static void main(String[] args) {
        new Project2().run();
    }
}
