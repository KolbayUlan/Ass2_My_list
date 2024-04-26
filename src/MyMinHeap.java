
public class MyMinHeap {
    // The underlying array that stores the items in the heap
    private MyArrayList<Integer> heap;

    // Constructs a new, empty MyMinHeap object
    MyMinHeap(){
        heap = new MyArrayList<>();
        heap.add(Integer.MIN_VALUE);
    }

    // This method prints the current heap tree in a human-readable format
    public void print()
    {
        for (int i = 1; i <= (heap.size() - 1) / 2; i++) {

            // Prints the parent and both childrens
            System.out.print(
                    " PARENT : " + heap.get(i)
                            + " LEFT CHILD : " + (leftChildOf(i) != Integer.MIN_VALUE ? heap.get(2 * i) : "Does not exist")
                            + " RIGHT CHILD :" + (rightChildOf(i) != Integer.MIN_VALUE ? heap.get(2 * i + 1) : "Does not exist"));

            // By here new line is required
            System.out.println();
        }
    }

    // Returns true if the heap is empty, and false otherwise
    public boolean empty(){
        return (heap.size() == 1);
    }

    // Returns the number of elements in the heap
    public int size(){
        return heap.size() - 1;
    }

    // Returns the minimum element in the heap
    public int getMin(){
        return heap.get(1);
    }

    // Removes and returns the minimum element from the heap
    public int extractMin(){
        if(empty()){
            System.out.println("The Min Heap is empty!");
            return Integer.MIN_VALUE;
        }
        int popped = getMin();
        swap(1, size());
        heap.removeLast();
        heapify(1);
        return popped;
    }

    // Inserts an element into the heap
    public void insert(int item){
        heap.addLast(item);
        traverseUp(size());
    }

    // Restores the heap property by "bubbling down" a node to its correct position in the heap
    private void heapify(int index){
        if (leftChildOf(index) != Integer.MIN_VALUE && rightChildOf(index) != Integer.MIN_VALUE) {
            if (rightChildOf(index) == Integer.MIN_VALUE || (heap.get(leftChildOf(index)) <= heap.get(rightChildOf(index)))) {
                swap(leftChildOf(index), index);
                heapify(leftChildOf(index));
            }
            else {
                swap(rightChildOf(index), index);
                heapify(rightChildOf(index));
            }
        }
    }

    // Maintains the heap property by comparing the parent of a node with the node, and swapping them if the parent is greater than the node
    private void traverseUp(int index){
        if (index != 1 && heap.get(parentOf(index)) > heap.get(index)){
            swap(parentOf(index), index);
            traverseUp(parentOf(index));
        }
    }

    // Calculates the index of the left child of a node
    private int leftChildOf(int index) {
        if (index * 2 > size()){
            return Integer.MIN_VALUE;
        }
        return index * 2;
    }

    // Calculates the index of the right child of a node
    private int rightChildOf(int index){
        if (index * 2 + 1 > size()){
            return Integer.MIN_VALUE;
        }
        return index * 2 + 1;
    }

    // Calculates the index of the parent of a node
    private int parentOf(int index){
        if (index / 2 < 1){
            return Integer.MIN_VALUE;
        }
        return (int)(index / 2);
    }

    // Swaps the elements at two indices in the heap array
    private void swap(int index1, int index2){
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
