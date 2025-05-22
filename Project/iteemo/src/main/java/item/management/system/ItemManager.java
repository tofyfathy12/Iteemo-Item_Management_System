package item.management.system;

class Item {
    private Integer ID;
    private String name, desc, category;
    private boolean priority;

    public Item(Integer id, String name, String desc, String category, boolean priority) {
        this.ID = id;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.priority = priority;
    }

    public Integer getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String newDesc) {
        this.desc = newDesc;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public boolean getPriority() {
        return this.priority;
    }

    public void setPriority(boolean newPriority) {
        this.priority = newPriority;
    }
}

public class ItemManager {
    DLL<Item> itemsDll = new DLL<Item>();
    BinarySearchTree<Integer, DLLNode<Item>> bst = new BinarySearchTree<Integer, DLLNode<Item>>();
    

    public void addItem(String name, String description, String category, String priority) {

    }

    public void viewItemById(int id) {

    }

    public void viewAllItems() {

    }

    public void updateItem(int id, String newName, String newDescription, String newCategory, String newPriority) {

    }

    public void deleteItem(int id) {

    }

    public void undoLastDeletion() {

    }

    public void processNextPriorityItem() { // Dequeue next urgent/normal item
    
    }

    public void searchItemByName(String name) {

    }
    public void searchItemByCategory(String category) {

    }
    public void saveToFile(String filename) {

    }
    public void loadFromFile(String filename) {

    }
}
