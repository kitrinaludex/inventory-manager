package io.github.kitrinaludex.inventory_manager.model;

import java.util.List;

public class Inventory {
    private long id;
    private long userId;
    private String name;
    private int itemQuantity;

    public int getItemQuantity() {
        return itemQuantity;
    }

    public long getUserId() {
        return userId;
    }

    public Inventory(long id, long userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
