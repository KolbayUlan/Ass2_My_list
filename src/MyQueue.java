public class MyQueue<T extends Comparable<T>> {
    // The underlying linked list that stores the elements in the queue
    private MyLinkedList<T> queue;

    // Constructs a new, empty MyQueue object
    MyQueue(){
        queue = new MyLinkedList<>();
    }

    // Returns true if the queue is empty, and false otherwise
    boolean empty(){
        return size() == 0;
    }

    // Returns the number of elements in the queue
    int size(){
        return queue.size();
    }

    // Returns the first element in the queue without removing it
    T peek(){
        return queue.getFirst();
    }

    // Inserts an element into the queue
    T enqueue(T item){
        queue.addLast(item);
        return item;
    }

    // Removes and returns the first element in the queue
    T dequeue(){
        T removed = queue.getFirst();
        queue.removeFirst();
        return removed;
    }
}