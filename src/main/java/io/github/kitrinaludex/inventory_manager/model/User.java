package io.github.kitrinaludex.inventory_manager.model;

public class User {
    private long id;
    private String userName;

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
