package com.example.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    // In-memory "database"
    private Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    // --- GET /users ---
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    // --- GET /users/{id} ---
    // Restrict to digits so bad paths (e.g. /users/abc) return 404, not 400 from type mismatch
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = users.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();  // 404
        }
        return ResponseEntity.ok(user);  // 200
    }

    // --- POST /users ---
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        newUser.setId(nextId++);
        users.put(newUser.getId(), newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);  // 201
    }

    // --- PUT /users/{id} ---
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updated) {
        if (!users.containsKey(id)) {
            return ResponseEntity.notFound().build();  // 404
        }
        updated.setId(id);
        users.put(id, updated);
        return ResponseEntity.ok(updated);  // 200
    }

    // --- DELETE /users/{id} ---
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (!users.containsKey(id)) {
            return ResponseEntity.notFound().build();  // 404
        }
        users.remove(id);
        return ResponseEntity.noContent().build();  // 204
    }
}