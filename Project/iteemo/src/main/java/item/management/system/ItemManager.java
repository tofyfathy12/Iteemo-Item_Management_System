package item.management.system;

class Item {
    private Integer ID;
    private String name, desc, category;
    private int priority;

    public Item(Integer id, String name, String desc, String category, int priority) {
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

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }
}

public class ItemManager {
    private int itemCount =0;
    private DLL<Item> itemsDll = new DLL<Item>();
    private BinarySearchTree<Integer, DLLNode<Item>> itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
    private MyStack<DLLNode<Item>> undoStack;
    private LinkedPriorityQueue<Item> PQ = new LinkedPriorityQueue<>();

    public ItemManager() {
        this.itemsDll = new DLL<Item>();
        this.itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
        this.undoStack = new MyStack<DLLNode<Item>>();
    }

    public void addItem(String name, String description, String category,int priority) {
        Item newItem = new Item(itemCount, name, description, category, priority );
        DLLNode<Item> newNode = itemsDll.add(newItem);
        itemsBST.insert(newItem.getID(),newNode);
        
        itemCount++;

    }

    public void viewItemById(int ID) {

    }

    public void viewAllItems() {

    }

    public void updateItem(int ID, String newName, String newDescription, String newCategory, String newPriority) {

    }

    public void deleteItem(int ID) {

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
