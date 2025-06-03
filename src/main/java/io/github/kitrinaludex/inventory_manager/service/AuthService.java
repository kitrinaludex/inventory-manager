package io.github.kitrinaludex.inventory_manager.service;

import io.github.kitrinaludex.inventory_manager.exceptions.InventoryAccessDeniedException;
import io.github.kitrinaludex.inventory_manager.exceptions.InventoryNotFoundExeption;
import io.github.kitrinaludex.inventory_manager.repository.PermissionRepository;
import io.github.kitrinaludex.inventory_manager.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    InventoryService inventoryService;

    public void setRole(long userId,long inventoryId,String role) {
        permissionRepository.setRole(userId,inventoryId,role);
    }

    public boolean checkInventoryAccess(long inventoryId,String requiredRole) throws InventoryAccessDeniedException {
        if (!inventoryService.exists(inventoryId)) {
            throw new InventoryNotFoundExeption(inventoryId);
        }
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        long userId = user.getId();
        if (permissionRepository.hasPermission(userId,inventoryId,requiredRole)) {
            return true;
        }else throw new InventoryAccessDeniedException("You dont have the requried permissions for" +
                "this action");
    }
}
