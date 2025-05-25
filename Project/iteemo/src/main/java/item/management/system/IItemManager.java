package item.management.system;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IItemManager {
    void addItem(int ID, String name, String description, String category, int priority);
    void viewItemById(int id);
    void viewAllItems();
    void updateItem(int ID, String newName, String newDescription, String newCategory, int newPriority);
    void deleteItem(int ID);
    void undoLastDeletion();
    void searchItemByName(String name);
    void searchItemByCategory(String category);
    void saveToFile(Item item) throws IOException;
    void loadFromFile() throws FileNotFoundException, IOException;
}
