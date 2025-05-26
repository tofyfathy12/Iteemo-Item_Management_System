import java.io.*;
import java.sql.*;

/**
 * Represents an item with an ID, name, description, category, and priority.
 * This class is used by the ItemManager to store item details.
 */
class Item {
    private Integer ID;
    private String name, desc, category;
    private int priority;

    /**
     * Constructs a new Item.
     * @param id the unique identifier for the item
     * @param name the name of the item
     * @param desc a description of the item
     * @param category the category of the item
     * @param priority the priority of the item
     */
    public Item(Integer id, String name, String desc, String category, int priority) {
        this.ID = id;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.priority = priority;
    }

    /**
     * Gets the ID of the item.
     * @return the item's ID
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Gets the name of the item.
     * @return the item's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the item.
     * @param newName the new name for the item
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the description of the item.
     * @return the item's description
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Sets the description of the item.
     * @param newDesc the new description for the item
     */
    public void setDesc(String newDesc) {
        this.desc = newDesc;
    }

    /**
     * Gets the category of the item.
     * @return the item's category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets the category of the item.
     * @param newCategory the new category for the item
     */
    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    /**
     * Gets the priority of the item.
     * @return the item's priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority of the item.
     * @param newPriority the new priority for the item
     */
    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }
}

/**
 * Manages a collection of items using a Doubly Linked List for sequential access and iteration,
 * a Binary Search Tree for fast lookups by ID, a Priority Queue for viewing items by priority,
 * and a Stack for undoing deletions.
 * Implements the IItemManager interface.
 */
public class ItemManager implements IItemManager{
    private DLL<Item> itemsDll = new DLL<Item>();
    private BinarySearchTree<Integer, DLLNode<Item>> itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
    private MyStack<DLLNode<Item>> undoStack;
    private LinkedPriorityQueue<Item> itemsPQ = new LinkedPriorityQueue<>();
    private ItemDBHandler db = new ItemDBHandler("iteemo.db");
    private Connection connection; // Get the database connection from the ItemDBHandler
    
    /**
     * Constructs a new ItemManager, initializing the data structures.
     */
    public ItemManager() {
        this.itemsDll = new DLL<Item>();
        this.itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
        this.undoStack = new MyStack<DLLNode<Item>>();
        connection = db.connect();
        db.createTable();
    }

    /**
     * Adds a new item to the system.
     * The item is added to the DLL, BST (ID -> DLLNode), and Priority Queue.
     * @param isNew indicates if the item is new (true) or imported (false)
     * @param ID the unique identifier for the item
     * @param name the name of the item
     * @param description a description of the item
     * @param category the category of the item
     * @param priority the priority of the item
     * @return true if the item was added successfully, false if an item with the same ID already exists.
     */
    public boolean addItem(boolean isNew,int ID, String name, String description, String category, int priority) {
        Item newItem = new Item(ID, name, description, category, priority);
        if (itemsBST.get(ID) != null) {
            System.out.println("Item with ID = " + ID + " already exists !!");
            return false;
        }
        DLLNode<Item> newNode = itemsDll.add(newItem);
        itemsBST.insert(newItem.getID(),newNode);
        itemsPQ.insert(priority, newItem);
        if (isNew) {
            db.insertItem(newItem);
        }
        return true;
    }

    /**
     * Displays the details of an item specified by its ID.
     * Retrieves the item from the BST.
     * @param ID the ID of the item to view.
     */
    public void viewItemById(int ID) {
        DLLNode<Item> curr = itemsBST.get(ID);
        if (curr != null) {
            System.out.println("---------------------------------------------------------------------");
            System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
            System.out.println("---------------------------------------------------------------------");
            System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            System.out.println("---------------------------------------------------------------------");
        } else {
            System.out.println("Item not found.");
        }
    }

    /**
     * Displays all items in the system, ordered by priority (highest first).
     * Iterates through the priority queue.
     */
    public void viewAllItems() {
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
        System.out.println("---------------------------------------------------------------------");
        PQNode<Item> curr = itemsPQ.getHead();
        while (curr != null) {
            System.out.printf("| %d | %s | %s | %s |\n", curr.getData().getID(), curr.getData().getName(), curr.getData().getDesc(), curr.getData().getCategory());
            curr = curr.getNext();
        }
        System.out.println("---------------------------------------------------------------------\n");
    }

    public boolean itemExists(int ID) {
        DLLNode<Item> curr = itemsBST.get(ID);
        if (curr != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the details of an existing item identified by its ID.
     * Only non-null new values are used for updating.
     * The priority queue might need re-ordering if priority changes.
     * @param ID the ID of the item to update
     * @param newName the new name (if null, name is not changed)
     * @param newDescription the new description (if null, description is not changed)
     * @param newCategory the new category (if null, category is not changed)
     * @param newPriority the new priority (if null, priority is not changed)
     */
    public void updateItem(int ID, String newName, String newDescription, String newCategory, Integer newPriority) {
        DLLNode<Item> targetNode = itemsBST.get(ID);
        Item item = targetNode.getElement();
        if (targetNode != null) {
            if (newName != null)
                item.setName(newName);
            if (newDescription != null)
                item.setDesc(newDescription);
            if (newCategory != null)
                item.setCategory(newCategory);
            if (newPriority != null){
                item.setPriority(newPriority);
                itemsPQ.remove(item); // Remove the item from the priority queue
                itemsPQ.insert(newPriority, item); // Reinsert it with the new priority
            }
            db.updateItem(item);    
        }
    }

    /**
     * Deletes an item from the system based on its ID.
     * The item is removed from the BST, DLL, and Priority Queue.
     * The deleted item's DLLNode is pushed onto the undo stack.
     * @param ID the ID of the item to delete
     */
    public void deleteItem(int ID) {
        DLLNode<Item> deletedItemNode = itemsBST.get(ID);
        itemsBST.delete(ID);
        DLLNode<Item> prev = null, next = null;
        if (deletedItemNode != null) {
            undoStack.push(deletedItemNode);
            itemsPQ.remove(deletedItemNode.getElement());
            prev = deletedItemNode.getPrev();
            next = deletedItemNode.getNext();
            db.deleteItem(deletedItemNode.getElement().getID());
            if (prev != null)
                prev.setNext(next);
            if (next != null)
                next.setPrev(prev);
            itemsDll.size--;
        System.out.println("Item with ID " + ID + " deleted successfully.");
        }
    }

    /**
     * Undoes the last deletion.
     * Pops an item from the undo stack and re-inserts it into the DLL, BST, and Priority Queue.
     */
    public void undoLastDeletion() {
        if (undoStack.isEmpty()) {
            System.out.println("No deletions to undo.");
            return;
        }
        DLLNode<Item> lastDeleted = undoStack.pop();
        itemsBST.insert(lastDeleted.getElement().getID(), lastDeleted);
        itemsPQ.insert(lastDeleted.getElement().getPriority(), lastDeleted.getElement());
        db.insertItem(lastDeleted.getElement());
        DLLNode<Item> prev = lastDeleted.getPrev(), next = lastDeleted.getNext();
        if (prev != null)
            prev.setNext(lastDeleted);
        if (next != null)
            next.setPrev(lastDeleted);
        System.out.println("Last deletion undone successfully.");    
    }

    /**
     * Searches for items by name and prints their details.
     * Iterates through the DLL.
     * @param name the name of the item(s) to search for.
     */
    public DLL<Item> searchItemByName(String name) {
        DLLNode<Item> curr = itemsDll.getHead();
        DLL<Item> resultsDll = new DLL<Item>();
        int ResultsCount = 0;
        name = name.toLowerCase(); // Convert search term to lowercase for case-insensitive matching
        while (curr != null) {
            if (curr.getElement().getName().toLowerCase().contains(name)) {
                if (ResultsCount == 0) {
                    System.out.println("------------------Search Results------------------");
                    System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
                    System.out.println("--------------------------------------------------");
                }
                ResultsCount++;
                resultsDll.add(curr.getElement()); // Add to results DLL
                System.out.printf("| %d | %s | %s | %s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            }
            curr = curr.getNext();
        }
        if (ResultsCount == 0) {
            System.out.println("No items found with the name: " + name);
            return null; // Return null if no items found
        } else {
            System.out.println("--------------------------------------------------");
            return resultsDll; // Return the DLL containing the items found
        }
    }

    /**
     * Searches for items by category and prints their details.
     * Iterates through the DLL.
     * @param category the category of the item(s) to search for.
     */
    public DLL<Item> searchItemByCategory(String category) {
        DLL<Item> resultsDll = new DLL<Item>();
        DLLNode<Item> curr = itemsDll.getHead();
        category = category.toLowerCase(); // Convert search term to lowercase for case-insensitive matching
        int ResultsCount = 0;
        while (curr != null) {
            if (curr.getElement().getCategory().toLowerCase().contains(category)) {
                if (ResultsCount == 0) {
                    System.out.println("------------------Search Results------------------");
                    System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
                    System.out.println("--------------------------------------------------");
                }
                ResultsCount++;
                resultsDll.add(curr.getElement()); // Add to results DLL
                System.out.printf("| %d | %s | %s | %s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            }
            curr = curr.getNext();
        }
        if (ResultsCount == 0) {
            System.out.println("No items found in the category: " + category);
            return null; // Return null if no items found
        } else {
            System.out.println("--------------------------------------------------");
            return resultsDll; // Return the DLL containing the items found
        }
    }
    /**
     * Loads items from a CSV file named "Items.csv".
     * If the file doesn't exist, it creates a new one.
     * Each line in the CSV is expected to be: ID,name,description,category,priority
     * @throws IOException if an error occurs during file reading.
     */
    /**
     * Helper method to save a single item to the "Items.csv" file.
     * @param item the item to save.
     * @param isFirst true if this is the first item being written (overwrites file), false to append. Note: the boolean parameter name in original code is `isFirst` but used as `!isFirst` for append flag, which is slightly confusing. Javadoc reflects its usage.
     * @throws IOException if an error occurs during file writing.
     */
    public void saveToFileHelper (Item item, boolean isFirst) throws IOException { // Parameter 'isFirst' actually means 'append' if it's *false*.
        try (FileWriter fileWriter = new FileWriter("Items.csv", isFirst)) { // 'isFirst' here determines if it appends or overwrites. Original uses 'isFirst' from calling context for append flag.
            fileWriter.write(item.getID() + "," + item.getName() + "," + item.getDesc() + "," + item.getCategory() + "," + item.getPriority() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves all current items to "Items.csv".
     * Iterates through the items in the DLL and uses the helper method to write each one.
     * The first item overwrites the file, subsequent items are appended.
     * @throws IOException if an error occurs during file writing.
     */
    public void saveToFile (DLL<Item> Dll) throws IOException {
        DLLNode<Item> current = Dll.getHead();
        boolean isFirst = true;
        while (current != null) {
            saveToFileHelper(current.getElement(), !isFirst); // `!isFirst` is used as the append flag for FileWriter
            current = current.getNext();
            isFirst = false;
        }
    }
     public void loadfromDB() {
        // SQL statement to select all records
        String sql = "SELECT id, name, description, category, priority FROM items";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { // ResultSet is AutoCloseable

            // Loop through the result set
            while (rs.next()) {
                addItem(
                    false,rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getInt("priority")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all items: " + e.getMessage());
            // items list will be empty or partially filled if error occurs mid-loop
        }
        return;
    }
    /**
     * Closes the database connection.
     */
    public void closeDB() {
        db.closeConnection();
    }
}