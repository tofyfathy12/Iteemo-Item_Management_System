package item.management.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
<<<<<<< HEAD
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ItemManager manager = new ItemManager();
        manager.loadFromFile();
        manager.printMenu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter ID:");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Name:");
                String name = scanner.nextLine();
                System.out.print("Enter Description:");
                String description = scanner.nextLine();
                System.out.print("Enter Category:");
                String category = scanner.nextLine();
                System.out.print("Enter Priority(1 is Highest, 3 is Lowest Priority):");
                int priority = scanner.nextInt();
                manager.addItem(id, name, description, category, priority);
                System.out.println("Item added successfully.");
                try {
                    manager.saveToFile(new Item(id, name, description, category, priority));
                } catch (Exception e) {
                    System.out.println("Error saving items: " + e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter ID:");
                int removeId = scanner.nextInt();
                manager.deleteItem(removeId);
                break;
            case 3:
                System.out.print("Enter ID:");
                int viewId = scanner.nextInt();
                
                manager.updateItem(viewId, name, description, category, priority);


    }
}
}