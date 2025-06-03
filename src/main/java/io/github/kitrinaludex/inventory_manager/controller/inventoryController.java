package io.github.kitrinaludex.inventory_manager.controller;

import io.github.kitrinaludex.inventory_manager.model.Inventory;
import io.github.kitrinaludex.inventory_manager.model.Item;
import io.github.kitrinaludex.inventory_manager.model.User;
import io.github.kitrinaludex.inventory_manager.security.SecurityUser;
import io.github.kitrinaludex.inventory_manager.service.AuthService;
import io.github.kitrinaludex.inventory_manager.service.InventoryService;
import io.github.kitrinaludex.inventory_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class inventoryController {

    @Autowired
    public inventoryController(InventoryService inventoryService,
                               UserService userService,AuthService authService) {
        this.inventoryService = inventoryService;
        this.userService = userService;
        this.authService = authService;
    }


    private final InventoryService inventoryService;
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/inventories/{id}")
    public ResponseEntity<?> getItems(@PathVariable long id) {
        if (!(inventoryService.exists(id))) {
            return new ResponseEntity<String>("The requested inventory does not exist",
                    HttpStatus.NOT_FOUND);
        }

        if (authService.checkInventoryAccess(id,"VIEWER")) {
            return ResponseEntity.ok(inventoryService.getInventory(id));
        }else
            return new ResponseEntity<String>("Unauthorized", HttpStatus.FORBIDDEN);
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
        return "Your name is " + username;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        Long newUserId = userService.addUser(user);
        return ResponseEntity.created(URI.create("users/" + newUserId)).build();
    }

    @PostMapping("/inventories")
    public ResponseEntity<?> createInventory(@RequestBody Inventory inventory) {
        SecurityUser user = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();

        Long newInventoryId = inventoryService.createInventory(inventory.
                getName(),user.getId());

        return ResponseEntity.created(URI.create("inventories/" + newInventoryId)).build();
    }

    @PostMapping("/inventories/{id}")
    public ResponseEntity<?> createEntry(@PathVariable long id,
                                            @RequestBody Item item) {
        if (!(inventoryService.exists(id))) {
            return new ResponseEntity<String>("The requested inventory does not exist",
                    HttpStatus.NOT_FOUND);
        }
        if (authService.checkInventoryAccess(id,"EDITOR")) {

            Long newItemId = inventoryService.createItem(id, item);
            return ResponseEntity.created(URI.create("inventories/" + id
                            + "/items/" + newItemId))
                    .build();
        }else
            return new ResponseEntity<String>("You don't have the required permissions" +
                    "for the request",HttpStatus.FORBIDDEN);
    }

    @PutMapping("/inventories/{id}/items/{itemid}")
    public ResponseEntity<?> updateItem(@PathVariable long id,
                                               @PathVariable long itemid,
                                               @RequestBody Item item){
        if (!(inventoryService.exists(id))) {
            return new ResponseEntity<String>("The requested inventory does not exist",
                    HttpStatus.NOT_FOUND);
        }
        inventoryService.updateItem(itemid, item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inventories/{id}/items/{itemid}")
    public  ResponseEntity<?> deleteItem(@PathVariable("itemid") long id){
        if (!(inventoryService.exists(id))) {
            return new ResponseEntity<String>("The requested inventory does not exist",
                    HttpStatus.NOT_FOUND);
        }
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}


