package item.management.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ItemManager manager = new ItemManager();
        manager.loadFromFile();
        ConsoleMenu menu = new ConsoleMenu();
        menu.clearScreen();

        menu.start();

        while (true) {
            menu.printMenu();

            int input = System.in.read();
            Scanner scanner = new Scanner(System.in);
            menu.cleanBuffer();

            if (input == 'w' || input == 'W') {
                menu.moveUp();
            } else if (input == 's' || input == 'S') {
                menu.moveDown();
            } else if (input == '\n' || input == '\r') {
                switch (menu.selected) {
                    case 0:
                        System.out.println(menu.FG_GREEN + "Adding a new Item..." + menu.RESET);
                        System.out.print("Enter ID:");
                        if (!scanner.hasNextInt()) {
                            System.out.println(menu.FG_RED + "Invalid ID. Please enter a valid integer." + menu.RESET);
                            scanner.nextLine();
                            continue;
                        }
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
                        int addState = manager.addItem(id, name, description, category, priority);
                        if (addState == 1)
                            System.out.println("Item added successfully.");
                        menu.pause();
                        break;
                    case 1:
                        System.out.println(menu.FG_GREEN + "Deleting an Item..." + menu.RESET);
                        menu.pause();
                        break;
                    case 2:
                        System.out.println(menu.FG_GREEN + "Updating an Item..." + menu.RESET);
                        menu.pause();
                        break;
                    case 3:
                        manager.viewAllItems();
                        menu.pause();
                        break;
                    case 4:
                        System.out.println(menu.FG_GREEN + "Searching Items..." + menu.RESET);
                        menu.pause();
                        break;
                    case 5:
                        System.out.println(menu.FG_GREEN + "Undoing last Deletion..." + menu.RESET);
                        menu.pause();
                        break;
                    case 6:
                        menu.clearScreen();
                        System.out.print(menu.FG_RED + "Sure you want to exit? (y/n)" + menu.RESET);
                        Scanner exit = new Scanner(System.in);
                        String confirmation = exit.nextLine().trim().toLowerCase();
                        if (confirmation.equals("y") || confirmation.equals("yes")) {
                            menu.clearScreen();
                            System.out.println(menu.FG_YELLOW + "Thank you for using ITEEMO!" + menu.RESET);
                            menu.close();
                            scanner.close();
                        } else {
                            menu.clearScreen();
                            System.out.println(menu.FG_YELLOW + "Returning to the main menu..." + menu.RESET);
                            menu.printMenu();
                        }
                        
                        break;
                }
            }
        }
}
}