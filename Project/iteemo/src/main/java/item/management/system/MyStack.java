package item.management.system;

import java.util.regex.*;
import java.util.Scanner;
import java.util.EmptyStackException;

class Node {
    private Object data;
    private Node next;

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setData(Object newData) {
        data = newData;
    }

    public void setNext(Node newNext) {
        next = newNext;
    }
}


public class MyStack implements IStack {
    private int size = 0;
    private Node top = null;
    public Object pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        Node poppedNode = top;
        top = top.getNext();
        poppedNode.setNext(null);
        size--;
        return poppedNode.getData();
    }

    public Object peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getData();
    }

    public void push(Object element) {
        if (top == null) {
            top = new Node(element, null);
        }
        else {
            Node newNode = new Node(element, top);
            top = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printStack() {
        Node head = top;
        System.out.print("[");
        while (head != null) {
            System.out.print(head.getData());
            if (head.getNext() != null) {
                System.out.print(", ");
            }
            head = head.getNext();
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        Pattern p = Pattern.compile("\\[(.*)\\]");
        Matcher m = p.matcher(input);

        MyStack mystack = new MyStack();
        try {
            if (m.matches()) {
                char[] listCharArray = m.group(1).replaceAll(", *", "_").toCharArray();
                int i = listCharArray.length - 1;
                while (i >= 0) {
                    String numString = "";
                    while (i >= 0 && (Character.isDigit(listCharArray[i]) || listCharArray[i] == '-')) {
                        numString += listCharArray[i];
                        i--;
                    }
                    String orgNumString = "";
                    char[] numChars = numString.toCharArray();
                    for (int j = numChars.length - 1; j >= 0 ; j--) {
                        orgNumString += numChars[j];
                    }
                    mystack.push(Integer.parseInt(orgNumString));
                    i--;
                }
            }
    
            String op = scan.nextLine();
            int element;
            switch (op) {
                case "push":
                    element = scan.nextInt();
                    mystack.push(element);
                    mystack.printStack();
                    break;
                case "pop":
                    element =(int)mystack.pop();
                    mystack.printStack();
                    break;
                case "peek":
                    System.out.println(mystack.peek());
                    break;
                case "isEmpty":
                    String s = (mystack.isEmpty()) ? "True" : "False";
                    System.out.println(s);
                    break;
                case "size":
                    System.out.println(mystack.size());
                    break;
            }
            scan.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}
