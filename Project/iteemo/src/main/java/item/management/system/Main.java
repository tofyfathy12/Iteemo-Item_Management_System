package item.management.system;

class Item {
    private String ID, name, desc, category;

    public Item(String id, String name, String desc, String category) {
        this.ID = id;
        this.name = name;
        this.desc = desc;
        this.category = category;
    }

    public String getID() {
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
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}