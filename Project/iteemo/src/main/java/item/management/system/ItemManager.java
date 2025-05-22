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
    private DLL<Item> itemsDll;
    private BinarySearchTree<Integer, DLLNode<Item>> itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
    private MyStack<DLLNode<Item>> undoStack;

    public ItemManager() {
        this.itemsDll = new DLL<Item>();
        this.itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
        this.undoStack = new MyStack<DLLNode<Item>>();
    }
    
    public void addItem(String name, String description, String category, String priority) {

    }

    public void viewItemById(int id) {
        
    }

    public void viewAllItems() {

    }

    public void updateItem(int id, String newName, String newDescription, String newCategory, String newPriority) {

    }

    public void deleteItem(int id) {
        DLLNode<Item> deletedItemNode = itemsBST.get(id);
        itemsBST.delete(id);
        DLLNode<Item> prev = null, next = null;
        if (deletedItemNode != null) {
            prev = deletedItemNode.getPrev();
            next = deletedItemNode.getNext();
        }
        if (prev != null)
            prev.setNext(next);
        if (next != null)
            next.setPrev(prev);
        itemsDll.size--;
        undoStack.push(deletedItemNode);
    }

    public void undoLastDeletion() {
        DLLNode<Item> lastDeleted = undoStack.pop();

        itemsBST.insert(lastDeleted.getElement().getID(), lastDeleted);

        DLLNode<Item> prev = lastDeleted.getPrev(), next = lastDeleted.getNext();
        if (prev != null)
            prev.setNext(lastDeleted);
        if (next != null)
            next.setPrev(lastDeleted);
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
