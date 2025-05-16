package io.github.kitrinaludex.inventory_manager.model;

public class Item {
    private final long  id;
    private String name;
    private int quantity;

    public Item(long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
