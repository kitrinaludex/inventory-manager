package io.github.kitrinaludex.inventory_manager.dto;

public class    InventoryEntryDto {
    private long id;
    private String name;
    private Integer quantity;

    public InventoryEntryDto(long id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
