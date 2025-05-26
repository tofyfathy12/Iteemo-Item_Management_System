import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Item Management System.
 * This class initializes the {@link ItemManagerV1} and {@link ConsoleMenu},
 * loads data from a file, and then enters a loop to display the menu and process user input.
 */
public class Main {
    /**
     * The main entry point for the application.
     * It sets up the item manager, loads initial data, and starts the console menu interaction loop.
     * Handles user input for navigating the menu and performing item management operations.
     * @param args command-line arguments (not used).
     * @throws FileNotFoundException if the data file ("Items.csv") is not found during loading.
     * @throws IOException if an I/O error occurs during file operations or console input.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ItemManager manager = new ItemManager();
        manager.loadfromDB();
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
                        if (!manager.itemExists(id)) {
                            System.out.println(menu.FG_RED + "Item with ID " + id + "already exists !!" + menu.RESET);
                            menu.pause();
                            continue; 
                        }
                        scanner.nextLine();
                        System.out.print("Enter Name:");
                        String name = scanner.nextLine();
                        System.out.print("Enter Description:");
                        String description = scanner.nextLine();
                        System.out.print("Enter Category:");
                        String category = scanner.nextLine();
                        System.out.print("Enter Priority(1 is Lowest):");
                        int priority = scanner.nextInt();
                        boolean isAdded = manager.addItem(true,id, name, description, category, priority);
                        if (!isAdded) {
                            System.out.println(menu.FG_RED + "Failed to add item. Please check the details and try again." + menu.RESET);
                            menu.pause();
                            continue;
                        }
                        System.out.println("Item added successfully.");
                        menu.pause();
                        break;
                    case 1:
                        System.out.println(menu.FG_GREEN + "Deleting an Item..." + menu.RESET);
                        System.out.print("Enter ID of the item to delete:");
                        if (!scanner.hasNextInt()) {
                            System.out.println(menu.FG_RED + "Invalid ID. Please enter a valid integer." + menu.RESET);
                            scanner.nextLine();
                            continue;
                        }
                        int deleteId = scanner.nextInt();
                        if (!manager.itemExists(deleteId)) {
                            System.out.println(menu.FG_RED + "Item with ID " + deleteId + " does not exist." + menu.RESET);
                            menu.pause();
                            continue; 
                        }
                        manager.deleteItem(deleteId);
                        menu.pause();
                        break;
                    case 2:
                        System.out.println(menu.FG_GREEN + "Updating an Item..." + menu.RESET);
                        System.out.print("Enter ID of the item to update:");
                        if (!scanner.hasNextInt()) {
                            System.out.println(menu.FG_RED + "Invalid ID. Please enter a valid integer." + menu.RESET);
                            scanner.nextLine();
                            continue;
                        }
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        if (!manager.itemExists(updateId)) {
                            System.out.println(menu.FG_RED + "Item with ID " + updateId + " does not exist." + menu.RESET);
                            menu.pause();
                            continue; 
                        }
                        System.out.print("Enter new Name (leave blank to keep current):");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new Description (leave blank to keep current):");
                        String newDescription = scanner.nextLine();
                        System.out.print("Enter new Category (leave blank to keep current):");
                        String newCategory = scanner.nextLine();
                        System.out.print("Enter new Priority (1 is Lowest, leave blank to keep current):");
                        String priorityInput = scanner.nextLine();
                        Integer newPriority = null;
                        if (!priorityInput.isEmpty()) {
                            if (!priorityInput.matches("\\d+")) {
                                System.out.println(menu.FG_RED + "Invalid Priority. Please enter a valid integer." + menu.RESET);
                                menu.pause();
                                continue;
                            }
                            newPriority = Integer.parseInt(priorityInput);
                        }
                        manager.updateItem(updateId, newName.isEmpty() ? null : newName, 
                                           newDescription.isEmpty() ? null : newDescription, 
                                           newCategory.isEmpty() ? null : newCategory, 
                                           newPriority);
                        menu.pause();
                        break;
                    case 3:
                        manager.viewAllItems();
                        menu.pause();
                        break;
                    case 4:
                        System.out.println(menu.FG_GREEN + "Searching Items..." + menu.RESET);
                        System.out.print("Enter search criteria (name/category) or just enter ID: ");
                        String searchInput = scanner.nextLine().trim();
                        if (searchInput.matches("\\d+")) {
                            int searchId = Integer.parseInt(searchInput);
                            manager.viewItemById(searchId);
                        } else if (searchInput.equalsIgnoreCase("category")) {
                            System.out.println(menu.FG_YELLOW + "Searching by category..." + menu.RESET);
                            System.out.print("Enter category name: ");
                            String categoryInput = scanner.nextLine().trim();    
                            DLL<Item> res = manager.searchItemByCategory(categoryInput);
                            System.out.print(menu.FG_YELLOW + "Do you want to save results in a csv file(y/n):" + menu.RESET);
                            String saveChoice = scanner.nextLine().trim().toLowerCase();
                            if (saveChoice.equals("y") || saveChoice.equals("yes")) {
                                try {
                                    manager.saveToFile(res);
                                    System.out.println(menu.FG_GREEN + "Results saved to Items.csv" + menu.RESET);
                                } catch (IOException e) {
                                    System.out.println(menu.FG_RED + "Error saving results: " + e.getMessage() + menu.RESET);
                                }
                            }
                        }
                        else if (searchInput.equalsIgnoreCase("name")) {
                            System.out.println(menu.FG_YELLOW + "Searching by name..." + menu.RESET);
                            System.out.print("Enter item name: ");
                            String nameInput = scanner.nextLine().trim();
                            DLL<Item> res = manager.searchItemByName(nameInput);
                            System.out.print(menu.FG_YELLOW + "Do you want to save results in a csv file(y/n):" + menu.RESET);
                            String saveChoice = scanner.nextLine().trim().toLowerCase();
                            if (saveChoice.equals("y") || saveChoice.equals("yes")) {
                                try {
                                    manager.saveToFile(res);
                                    System.out.println(menu.FG_GREEN + "Results saved to Items.csv" + menu.RESET);
                                } catch (IOException e) {
                                    System.out.println(menu.FG_RED + "Error saving results: " + e.getMessage() + menu.RESET);
                                }
                            }
                        }
                        else {
                            System.out.println(menu.FG_RED + "Invalid search criteria. Please enter a valid ID, name, or category." + menu.RESET);
                        }
                        menu.pause();
                        break;
                    case 5:
                        System.out.println(menu.FG_GREEN + "Undoing last Deletion..." + menu.RESET);
                        manager.undoLastDeletion();
                        menu.pause();
                        break;
                    case 6:
                        menu.clearScreen();
                        System.out.print(menu.FG_RED + "Sure you want to exit? (y/n)" + menu.RESET);
                        Scanner exit = new Scanner(System.in);
                        String confirmation = exit.nextLine().trim().toLowerCase();
                        if (confirmation.equals("y") || confirmation.equals("yes")) {
                            menu.clearScreen();
                            manager.closeDB();
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