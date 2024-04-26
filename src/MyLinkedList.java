import java.util.Iterator;

public class MyLinkedList<E extends Comparable<E>> implements MyList<E> {
    private MyNode<E> head;
    private MyNode<E> tail;
    private int size;

    // Constructs a new, empty MyLinkedList object
    public MyLinkedList(){

    }

    @Override
    public void add(E item) {
        addLast(item);
    }

    @Override
    public void set(int index, E item) {
        checkIndex(index);
        MyNode<E> current = getNode(index);
        current.data = item;
    }

    // Inserts an item at the specified index
    @Override
    public void add(int index, E item) {
        if(index == 0){
            addFirst(item);
        }
        else if(index == size){
            addLast(item);
        }
        else{
            checkIndex(index);
            MyNode<E> prev = getNode(index - 1);
            MyNode<E> newNode = new MyNode<>(item);
            newNode.next = prev.next;
            prev.next = newNode;
            newNode.prev = prev;
            if (newNode.next != null) {
                newNode.next.prev = newNode;
            } else {
                tail = newNode;
            }
            size++;
        }
    }

    // Adds an item to the beginning of the list
    @Override
    public void addFirst(E item) {
        MyNode<E> newNode = new MyNode<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E item) {
        MyNode<E> newNode = new MyNode<>(item);
        if(tail == null){
            tail = newNode;
            head = newNode;
        }
        else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        MyNode<E> current = getNode(index);
        return current != null ? current.data : null;
    }

    @Override
    public E getFirst() {
        return head != null ? head.data : null;
    }

    @Override
    public E getLast() {
        return tail != null ? tail.data : null;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        MyNode<E> current = getNode(index);
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }
        size--;
    }

    // Removes the first item in the list
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Removes the last item in the list
    @Override
    public void removeLast() {
        remove(size - 1);
    }

    // Sorts the list using a bubble sort algorithm
    @Override
    public void sort() {
        boolean swapped;
        do {
            swapped = false;
            MyNode<E> current = head;
            while (current.next != null) {
                if (current.data.compareTo(current.next.data) > 0) {
                    E temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    @Override
    public int indexOf(Object object) {
        MyNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        MyNode<E> current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyNode<E> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    // Clears the list, removing all items
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Returns the number of items in the list
    @Override
    public int size() {
        return size;
    }

    // Returns an iterator for the list
    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator<>();
    }

    // Throws an IndexOutOfBoundsException if the given index is out of bounds
    private void checkIndex(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index not correct");
    }

    // Returns the node at the specified index
    private MyNode<E> getNode(int index) {
        if (index >= 0 && index < size) {
            MyNode<E> current;
            if (index < size / 2) {
                current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            } else {
                current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
            }
            return current;
        } else {
            return null;
        }
    }

    // A private inner class that represents a node in the linked list
    private static class MyNode<E>{
        // The data stored in the node
        E data;
        // The next node in the linked list
        MyNode<E> next;
        // The previous node in the linked list
        MyNode<E> prev;

        // Constructs a new MyNode object with the given data
        MyNode(E data){
            this.data = data;
        }
    }

    // A private inner class that implements the Iterator interface
    private class MyLinkedListIterator<E> implements Iterator<E>{
        // The current node in the iteration
        private MyNode<E> current;

        // Returns true if there are more items to iterate over, and false otherwise
        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        //Returns the next item in the iteration
        @Override
        public E next() {
            if(!hasNext()){
                return null;
            }
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}