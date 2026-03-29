package com.example.api;

public class User {
    // Integer (not int): Jackson 3 rejects null/missing id in JSON for primitives (400 on POST)
    private Integer id;
    private String name;
    private String email;

    public User() {}

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId()        { return id; }
    public String getName()       { return name; }
    public String getEmail()      { return email; }

    public void setId(Integer id)      { this.id = id; }
    public void setName(String name)   { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}