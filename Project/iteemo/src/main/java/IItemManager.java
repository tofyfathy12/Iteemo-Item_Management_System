import java.io.IOException;

/**
 * Interface for an Item Manager.
 * Defines operations for managing a collection of items, including adding, viewing,
 * updating, deleting, searching, and persisting items to/from a file.
 */
public interface IItemManager {
    /**
     * Adds a new item to the manager.
     * @param ID the unique identifier for the item
     * @param name the name of the item
     * @param description a description of the item
     * @param category the category of the item
     * @param priority the priority of the item
     * @param isNew indicates if the item is new (true) or imported (false)
     * @return true if the item was added successfully, false otherwise (e.g., if ID already exists)
     */
    boolean addItem(boolean isNew,int ID, String name, String description, String category, int priority);

    /**
     * Views the details of a specific item by its ID.
     * Output is typically to the console.
     * @param id the ID of the item to view
     */
    void viewItemById(int id);

    /**
     * Views all items currently managed.
     * Output is typically to the console, often sorted by priority.
     */
    void viewAllItems();

    /**
     * Updates the details of an existing item.
     * Only non-null new values will be used for updating.
     * @param ID the ID of the item to update
     * @param newName the new name for the item (or null to keep existing)
     * @param newDescription the new description for the item (or null to keep existing)
     * @param newCategory the new category for the item (or null to keep existing)
     * @param newPriority the new priority for the item (or null to keep existing)
     */
    void updateItem(int ID, String newName, String newDescription, String newCategory, Integer newPriority);

    /**
     * Deletes an item from the manager based on its ID.
     * @param ID the ID of the item to delete
     */
    void deleteItem(int ID);

    /**
     * Undoes the last deletion operation, restoring the most recently deleted item.
     */
    void undoLastDeletion();

    /**
     * Searches for items by their name and displays the results.
     * Output is typically to the console.
     * @param name the name to search for (exact match expected in the current implementation)
     * @return a doubly linked list of items matching the search criteria
     */
    DLL<Item> searchItemByName(String name);

    /**
     * Searches for items by their category and displays the results.
     * Output is typically to the console.
     * @param category the category to search for (exact match expected in the current implementation)
     * @return a doubly linked list of items matching the search criteria
     */
    DLL<Item> searchItemByCategory(String category);

    /**
     * Saves items after search to a storage file (e.g., CSV).
     * @param Dll the doubly linked list of items to save
     * @throws IOException if an I/O error occurs during saving
     */
    void saveToFile(DLL<Item> Dll) throws IOException;
}