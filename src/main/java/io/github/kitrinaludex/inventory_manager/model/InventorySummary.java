package io.github.kitrinaludex.inventory_manager.model;

public class InventorySummary {
    long id;
    long ownerId;
    String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public InventorySummary(long id, String name, long ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }
}
