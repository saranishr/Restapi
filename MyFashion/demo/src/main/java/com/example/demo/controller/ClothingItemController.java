package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.clothingitemService;
import com.example.demo.model.ClothingItem;
@RestController
@RequestMapping("/api/clothing-items")
public class ClothingItemController {

    @Autowired
    private clothingitemService clothingItemService;

    @PostMapping
    public ResponseEntity<ClothingItem> addClothingItem(@RequestBody ClothingItem item) {
        ClothingItem createdItem = clothingItemService.addClothingItem(item);
        return ResponseEntity.status(201).body(createdItem);
    }

    @GetMapping
    public ResponseEntity<Page<ClothingItem>> getAllClothingItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClothingItem> items = clothingItemService.findAll(pageable);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ClothingItem>> searchClothingItems(@RequestParam String description, @RequestParam String size, Pageable pageable) {
        Page<ClothingItem> items = clothingItemService.searchClothingItems(description, size, pageable);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingItem> getClothingItemById(@PathVariable Long id) {
        ClothingItem item = clothingItemService.getClothingItemById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothingItem> updateClothingItem(@PathVariable Long id, @RequestBody ClothingItem item) {
        ClothingItem updatedItem = clothingItemService.updateClothingItem(id, item);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothingItem(@PathVariable Long id) {
        return clothingItemService.deleteClothingItem(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
