public class Main {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(20);
        stack.push(10);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.peek());

    }

}