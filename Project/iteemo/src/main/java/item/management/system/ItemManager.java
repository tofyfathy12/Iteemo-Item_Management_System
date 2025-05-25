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

public class ItemManager implements IItemManager{
    private DLL<Item> itemsDll = new DLL<Item>();
    private BinarySearchTree<Integer, DLLNode<Item>> itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
    private MyStack<DLLNode<Item>> undoStack;
    private LinkedPriorityQueue<Item> itemsPQ = new LinkedPriorityQueue<>();

    public ItemManager() {
        this.itemsDll = new DLL<Item>();
        this.itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
        this.undoStack = new MyStack<DLLNode<Item>>();
    }

    public void addItem(int ID, String name, String description, String category, int priority) {
        Item newItem = new Item(ID, name, description, category, priority);
        DLLNode<Item> newNode = itemsDll.add(newItem);
        itemsBST.insert(newItem.getID(),newNode);
        itemsPQ.insert(priority, newItem);
    }

    public void viewItemById(int ID) {
        DLLNode<Item> curr = itemsBST.get(ID);
        if (curr != null) {
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void viewAllItems() {
        System.out.println("--------------------------------------------------");
        System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
        System.out.println("--------------------------------------------------");
        PQNode<Item> curr = itemsPQ.getHead();
        while (curr != null) {
            System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getData().getID(), curr.getData().getName(), curr.getData().getDesc(), curr.getData().getCategory());
            curr = curr.getNext();
        }
        System.out.println("--------------------------------------------------\n");
    }

    public void updateItem(int ID, String newName, String newDescription, String newCategory, Integer newPriority) {
        DLLNode<Item> targetNode = itemsBST.get(ID);
        Item item = targetNode.getElement();
        if (item != null) {
            if (newName != null)
                item.setName(newName);
            if (newDescription != null)
                item.setDesc(newDescription);
            if (newCategory != null)
                item.setCategory(newCategory);
            if (newPriority != null)
                item.setPriority(newPriority);
        }
        else {
            System.out.println("Item with ID = " + ID + " is not found !!");
        }
    }

    public void deleteItem(int ID) {
        DLLNode<Item> deletedItemNode = itemsBST.get(ID);
        itemsBST.delete(ID);
        DLLNode<Item> prev = null, next = null;
        if (deletedItemNode != null) {
            undoStack.push(deletedItemNode);
            itemsPQ.remove(deletedItemNode.getElement());
            prev = deletedItemNode.getPrev();
            next = deletedItemNode.getNext();
        }
        if (prev != null)
            prev.setNext(next);
        if (next != null)
            next.setPrev(prev);
        itemsDll.size--;
    }

    public void undoLastDeletion() {
        DLLNode<Item> lastDeleted = undoStack.pop();

        itemsBST.insert(lastDeleted.getElement().getID(), lastDeleted);
        itemsPQ.insert(lastDeleted.getElement().getPriority(), lastDeleted.getElement());

        DLLNode<Item> prev = lastDeleted.getPrev(), next = lastDeleted.getNext();
        if (prev != null)
            prev.setNext(lastDeleted);
        if (next != null)
            next.setPrev(lastDeleted);
    }

    public void searchItemByName(String name) {
        DLLNode<Item> curr = itemsDll.getHead();
        int ResultsCount = 0;
        while (curr != null) {
            if (name.equals(curr.getElement().getName())) {
                if (ResultsCount == 0) {
                    System.out.println("------------------Search Results------------------");
                    System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
                    System.out.println("--------------------------------------------------");
                }
                ResultsCount++;
                System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            }
        }
        if (ResultsCount == 0) {
            System.out.println("No items found with the name: " + name);
        } else {
            System.out.println("--------------------------------------------------");
        }
    }

    public void searchItemByCategory(String category) {
        DLLNode<Item> curr = itemsDll.getHead();
        int ResultsCount = 0;
        while (curr != null) {
            if (category.equals(curr.getElement().getCategory())) {
                if (ResultsCount == 0) {
                    System.out.println("------------------Search Results------------------");
                    System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
                    System.out.println("--------------------------------------------------");
                }
                ResultsCount++;
                System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            }
        }
        if (ResultsCount == 0) {
            System.out.println("No items found in the category: " + category);
        } else {
            System.out.println("--------------------------------------------------");
        }
    }
    public void loadFromFile() throws IOException {
        File csv = new File("Items.csv");
        if (!csv.exists()) {
            throw new FileNotFoundException("File not found: " + csv.getAbsolutePath());
        }
        BufferedReader br = new BufferedReader(new FileReader(csv));
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            addItem(Integer.parseInt(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]));
        }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            br.close();
        }
        
    }
    public void saveToFile (Item item) throws IOException {
        try (FileWriter fileWriter = new FileWriter("Items.csv", true)) {
            fileWriter.write(item.getID() + "," + item.getName() + "," + item.getDesc() + "," + item.getCategory() + "," + item.getPriority() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printMenu() {
        System.out.println("1. Add Item");
        System.out.println("2. Delete Item");
        System.out.println("3. Update Item");
        System.out.println("4. View Items");
        System.out.println("5. Search Items");
        System.out.println("6. Undo Last Deletion");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }
}
