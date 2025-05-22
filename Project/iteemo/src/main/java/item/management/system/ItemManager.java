package item.management.system;

import java.io.*;

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
    private DLL<Item> itemsDll = new DLL<Item>();
    private BinarySearchTree<Integer, DLLNode<Item>> itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
    private MyStack<DLLNode<Item>> undoStack;
    private LinkedPriorityQueue<Item> itemsPQ = new LinkedPriorityQueue<>();

    public ItemManager() {
        this.itemsDll = new DLL<Item>();
        this.itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
        this.undoStack = new MyStack<DLLNode<Item>>();
    }

    public void addItem(int ID,String name, String description, String category,int priority) {
        Item newItem = new Item(ID, name, description, category, priority);
        DLLNode<Item> newNode = itemsDll.add(newItem);
        itemsBST.insert(newItem.getID(),newNode);
        itemsPQ.insert(priority, newItem);
        

    }

    public void viewItemById(int ID) {
        
    }

    public void viewAllItems() {

    }

    public void updateItem(int ID, String newName, String newDescription, String newCategory, String newPriority) {

    }

    public void deleteItem(int ID) {
        DLLNode<Item> deletedItemNode = itemsBST.get(ID);
        itemsBST.delete(ID);
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
        DLLNode<Item> curr = itemsDll.getHead();
        int ResultsCount =0;
        while (curr!=null) {
            if (category.equals(curr.getElement().getCategory())) {
                if (ResultsCount==0) {
                    System.out.println("");
                }
                ResultsCount++;
                System.out.printf(ResultsCount+". "+"ID: "+curr.getElement().getID()+"");
            }
        }
    }
    public void saveToFile(String filename) throws IOException {
        File csv = new File("Items.csv");
        BufferedReader br = new BufferedReader(new FileReader(csv));
        String line ="";
        while ((line = br.readLine()) != null) {
            String [] values = line.split(",");
            addItem(Integer.parseInt(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]));
        }
        br.close();
    }
    public void loadFromFile(String filename) throws FileNotFoundException {
        File csv = new File("Items.csv");
        DLLNode<Item> curr = itemsDll.getHead();
        PrintWriter out = new PrintWriter(csv);
        while (curr != null) {
            out.printf("%D,%S,%S,%S,%D", curr.getElement().getID(),curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory(),curr.getElement().getPriority());
        }
        out.close();
    }
}
