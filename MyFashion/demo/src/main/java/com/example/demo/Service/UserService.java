package com.example.demo.Service;

import com.example.demo.model.ClothingItem;
import com.example.demo.model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClothingItemRepository clothingItemRepository;

    // Save user without validation
    public User saveUser(User user) {
        if (user.getClothingItems() != null) {
            for (ClothingItem item : user.getClothingItems()) {
                item.setUser(user); // Ensure proper relationship
            }
        }
        return userRepository.save(user);
    }

    // Find user by username
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by email
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Find all users with pagination
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // Search users by name with pagination
    public Page<User> searchUsersByName(String name, Pageable pageable) {
        return userRepository.findByUsernameContaining(name, pageable);
    }

    // Insert user with clothing items
    public void insertUser(String username, String email, String password, List<ClothingItem> clothingItems) {
        User newUser = new User(username, email, password);
        if (clothingItems != null) {
            for (ClothingItem item : clothingItems) {
                item.setUser(newUser);
            }
            newUser.setClothingItems(clothingItems);
        }
        userRepository.save(newUser);
    }

    // Update user with clothing items
    public Optional<User> updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update only non-null fields
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }

            // Handle updates for ClothingItems
            if (user.getClothingItems() != null) {
                existingUser.getClothingItems().clear(); // Remove existing items
                for (ClothingItem item : user.getClothingItems()) {
                    item.setUser(existingUser);
                    existingUser.getClothingItems().add(item);
                }
            }

            return Optional.of(userRepository.save(existingUser));
        }
        return Optional.empty();
    }

    // Delete user and associated clothing items and transactions
    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            // Remove all clothing items first to avoid constraint issues
            clothingItemRepository.deleteAll(user.getClothingItems());

            // Delete the user itself
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    // Add clothing item to existing user
    public Optional<User> addClothingItem(Long userId, ClothingItem clothingItem) {
        return userRepository.findById(userId).map(user -> {
            clothingItem.setUser(user);
            user.getClothingItems().add(clothingItem);
            userRepository.save(user);
            return user;
        });
    }

    // Remove clothing item from existing user
    public Optional<User> removeClothingItem(Long userId, Integer clothingItemId) {
        return userRepository.findById(userId).map(user -> {
            user.getClothingItems().removeIf(item -> item.getId().equals(clothingItemId));
            userRepository.save(user);
            return user;
        });
    }
}
