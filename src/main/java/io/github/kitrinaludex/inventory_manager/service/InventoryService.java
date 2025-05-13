package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.dto.InventoryEntryDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InventoryService {

    public List<InventoryEntryDto> allEntries() {
        return Arrays.asList(
                new InventoryEntryDto(1,"ball",5),
                new InventoryEntryDto(2,"stick",3),
                new InventoryEntryDto(3,"table",1)
        );
    }
}
