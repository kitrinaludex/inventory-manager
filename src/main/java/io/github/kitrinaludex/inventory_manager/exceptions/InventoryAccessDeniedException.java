package io.github.kitrinaludex.inventory_manager.exceptions;

import java.nio.file.AccessDeniedException;

public class InventoryAccessDeniedException extends AccessDeniedException {
    public InventoryAccessDeniedException(String file) {
        super(file);
    }
}
