package item.management.system;

import java.util.Scanner;

class EmptyQueueException extends Exception {
    public EmptyQueueException(String message) {
        super(message);
    }
}

public class PriorityQueue {
    
    private int nItems, maxSize;
    private int[] queArray;

    public PriorityQueue(int size) {
        maxSize = size;
        queArray = new int[size];
        nItems = 0;
    }

    public int size() {
        return nItems;
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public void insert(int num) {
        if (nItems == 0) {
            queArray[nItems++] = num;
        }
        else {
            int i;
            for (i = nItems - 1; i >= 0; i--) {
                if (num > queArray[i]) {
                    queArray[i + 1] = queArray[i];
                }
                else {
                    break;
                }
            }
            queArray[i + 1] = num;
            nItems++;
        }
    }

    public int min() {
        return queArray[nItems - 1];
    }

    public int max() {
        return queArray[0];
    }

    public int removeMin() {
        int temp = queArray[nItems - 1];
        nItems--;
        return temp;
    }

    public int removeMax() {
        int maxItem = queArray[0];
        nItems--;
        for (int i = 0; i < nItems; i++) {
            queArray[i] = queArray[i + 1];
        }

        return maxItem;
    }

    public void printQue() {
        System.out.print("[");
        for (int i = 0; i < nItems; i++) {
            System.out.print(queArray[i]);
            if (i < nItems - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public PriorityQueue copy() {
        PriorityQueue copyque = new PriorityQueue(maxSize);
        for (int i = 0; i < nItems; i++) {
            copyque.insert(queArray[i]);
        }

        return copyque;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String firstLine = scan.nextLine();
        String[] size_queries = firstLine.split(" ");
        int arraySize = Integer.parseInt(size_queries[0]);
        int queriesNum = Integer.parseInt(size_queries[1]);

        String secondLine = scan.nextLine();
        String[] arrayElements = secondLine.split(" ");

        PriorityQueue myque = new PriorityQueue(arraySize);
        int sum = 0;
        for (int i = 0; i < arraySize; i++) {
            int element = Integer.parseInt(arrayElements[i]);
            myque.insert(element);
            sum += element;
        }


        for (int i = 0; i < queriesNum; i++) {
            int operationsNum = Integer.parseInt(scan.nextLine());
            PriorityQueue tempQue = myque.copy();
            int tempsum = sum;
            for (int j = 0; j < operationsNum; j++) {
                int min = tempQue.removeMin();
                int max = tempQue.removeMax();
                tempQue.insert(max - min);
                tempsum -= 2*min;
            }
            System.out.println(tempsum);
        }
        scan.close();
    }
}


