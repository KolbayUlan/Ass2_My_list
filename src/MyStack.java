public class MyStack<T extends Comparable<T>> {
    // The underlying linked list that stores the elements in the stack
    private MyLinkedList<T> stack;

    // Constructs a new, empty MyStack object
    MyStack(){
        stack = new MyLinkedList<>();
    }

    // Returns true if the stack is empty, and false otherwise
    boolean empty(){
        return size() == 0;
    }

    // Returns the number of elements in the stack
    int size(){
        return stack.size();
    }

    // Returns the first element in the stack without removing it
    T peek(){
        return stack.getFirst();
    }

    // Inserts an element into the stack
    T push(T item){
        stack.addFirst(item);
        return item;
    }

    // Removes and returns the first element in the stack
    T pop(){
        T removed = stack.getFirst();
        stack.removeFirst();
        return removed;
    }
}