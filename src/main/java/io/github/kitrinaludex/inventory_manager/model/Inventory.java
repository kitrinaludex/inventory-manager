package io.github.kitrinaludex.inventory_manager.model;

import java.util.List;

public class Inventory {
    private long id;
    private long userId;
    private String name;
    private List<Item> items;

    public long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Inventory(long id , String name, long userId, List<Item> items) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
