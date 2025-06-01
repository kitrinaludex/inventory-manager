package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.model.Inventory;
import io.github.kitrinaludex.inventory_manager.model.Item;
import io.github.kitrinaludex.inventory_manager.model.User;
import io.github.kitrinaludex.inventory_manager.security.SecurityUser;
import io.github.kitrinaludex.inventory_manager.service.InventoryService;
import io.github.kitrinaludex.inventory_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class inventoryController {

    @Autowired
    public inventoryController(InventoryService inventoryService,UserService userService) {
        this.inventoryService = inventoryService;
        this.userService = userService;
    }


    private final InventoryService inventoryService;
    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<?> getItems(@PathVariable long id) {
        return ResponseEntity.ok(inventoryService.getInventory(id));
    }

    @GetMapping("/inventories")
    public ResponseEntity<?> getInventoryList() {
        SecurityUser user = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();

        return ResponseEntity.ok(inventoryService.getInventoryList(user.getId()));
    }

    @GetMapping("/whoami")
    public String whoami() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
        return "Your name is +" + username;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("cool");
    }

    @PostMapping("/inventory")
    public ResponseEntity<Void> createInventory(@RequestBody Inventory inventory) {
    inventoryService.createInventory(inventory.getName(),inventory.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/inventory/{id}")
    public ResponseEntity<Item> createEntry(@PathVariable long id,
                                            @RequestBody Item item) {
        inventoryService.createItem(id, item);
        return ResponseEntity.created(URI.create("/inventory/item/" + id)).body(item);
    }

    @PutMapping("/inventory/{id}/item/{itemid}")
    public ResponseEntity<Void> updateItem(@PathVariable long id,
                                               @PathVariable long itemid,
                                               @RequestBody Item item){
        inventoryService.updateItem(itemid, item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inventory/{id}/item/{itemid}")
    public  ResponseEntity<Void> deleteItem(@PathVariable("itemid") long id){
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}


