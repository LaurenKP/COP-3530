
/**
 * Class to implement a priority queue of Country objects using a sorted array based on Happiness Index.
 *
 * @author <Lauren Palma>
 * @version <2023-10-06>
 */
public class PriorityQ {
    private Country[] queue;
    private int nItems;

    public PriorityQ(int size) {
        queue = new Country[size];
        nItems = 0;
    }

    public void insert(Country country) {
        int j;
        if (nItems == 0) {
            queue[nItems++] = country;
        } else {
            for (j = nItems - 1; j >= 0; j--) {
                if (country.getHappinessIndex() > queue[j].getHappinessIndex()) {
                    queue[j + 1] = queue[j];
                } else {
                    break;
                }
            }
            queue[j + 1] = country;
            nItems++;
        }
    }

    public Country remove() {
        if (!isEmpty()) {
            return queue[--nItems];
        } else {
            System.out.println("Priority Queue is empty");
            return null;
        }
    }

    public void printPriorityQ() {
        for (int i = nItems - 1; i >= 0; i--) {
            System.out.println(queue[i]);
        }
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == queue.length;
    }
}
