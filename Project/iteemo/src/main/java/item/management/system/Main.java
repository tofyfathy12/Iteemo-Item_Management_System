package item.management.system;

class Item {
    private Integer ID;
    private String name, desc, category;

    public Item(Integer id, String name, String desc, String category) {
        this.ID = id;
        this.name = name;
        this.desc = desc;
        this.category = category;
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
}

public class Main {

    DLL<Item> itemsDll = new DLL<Item>();
    BinarySearchTree<Integer, DLLNode<Item>> bst = new BinarySearchTree<Integer, DLLNode<Item>>();

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}