package io.github.kitrinaludex.inventory_manager.service;

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

    public void setRole(long userId,long inventoryId,String role) {
        permissionRepository.setRole(userId,inventoryId,role);
    }

    public boolean checkInventoryAccess(long inventoryId,String requiredRole){
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        long userId = user.getId();



        return permissionRepository.hasPermission(userId,inventoryId,requiredRole);
    }
}
