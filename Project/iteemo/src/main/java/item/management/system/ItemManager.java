package item.management.system;

public interface ItemManager {
    void addItem(String name, String description, String category, String priority);
    void viewItemById(int id);
    void viewAllItems();
    void updateItem(int id, String newName, String newDescription, String newCategory, String newPriority);
    void deleteItem(int id);
    void undoLastDeletion();
    void processNextPriorityItem(); // Dequeue next urgent/normal item
    void searchItemByName(String name);
    void searchItemByCategory(String category);
    void saveToFile(String filename);
    void loadFromFile(String filename);

}
