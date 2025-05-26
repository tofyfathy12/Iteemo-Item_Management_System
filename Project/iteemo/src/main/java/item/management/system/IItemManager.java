package item.management.system;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IItemManager {
<<<<<<< HEAD
    boolean addItem(int ID, String name, String description, String category, int priority);
=======
    int addItem(int ID, String name, String description, String category, int priority);
>>>>>>> 46d16e9c2a7a4d190d820d1644857d6d1cf6e8f6
    void viewItemById(int id);
    void viewAllItems();
    void updateItem(int ID, String newName, String newDescription, String newCategory, Integer newPriority);
    void deleteItem(int ID);
    void undoLastDeletion();
    void searchItemByName(String name);
    void searchItemByCategory(String category);
    void saveToFile(Item item) throws IOException;
    void loadFromFile() throws FileNotFoundException, IOException;
}
