package io.github.kitrinaludex.inventory_manager.exceptions;

public class InventoryNotFoundExeption extends RuntimeException{
    public InventoryNotFoundExeption(Long id) {
        super("Inventory not found with ID " + id);
    }
}
