
/**
 * Class to implement a stack of Country objects using an array.
 *
 *  @author <Lauren Palma>
 * @version <2023-10-06>
 */
public class Stack {
    private Country[] stack;
    private int top;

    public Stack(int size) {
        stack = new Country[size];
        top = -1;
    }

    public void push(Country country) {
        if (!isFull()) {
            stack[++top] = country;
        } else {
            System.out.println("Stack is full");
        }
    }

    public Country pop() {
        if (!isEmpty()) {
            return stack[top--];
        } else {
            System.out.println("Stack is empty");
            return null;
        }
    }

    public void printStack() {
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }
}
