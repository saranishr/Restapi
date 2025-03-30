// ClothingItemService.java
package com.example.demo.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.ClothingItemRepository;
import com.example.demo.model.ClothingItem;
@Service
public class clothingitemService {
@Autowired
    private ClothingItemRepository clothingItemRepository;

    public ClothingItem addClothingItem(ClothingItem item) {
        return clothingItemRepository.save(item);
    }

    public Page<ClothingItem> findAll(Pageable pageable) {
        return clothingItemRepository.findAll(pageable);
    }

    public Page<ClothingItem> searchClothingItems(String description, String size, Pageable pageable) {
        return clothingItemRepository.findByDescriptionContainingAndSize(description, size, pageable);
    }

    public ClothingItem getClothingItemById(Long id) {
        return clothingItemRepository.findById(id).orElse(null);
    }

    public ClothingItem updateClothingItem(Long id, ClothingItem updatedItem) {
        return clothingItemRepository.findById(id).map(item -> {
            item.setDescription(updatedItem.getDescription());
            item.setSize(updatedItem.getSize());
            return clothingItemRepository.save(item);
        }).orElse(null);
    }

    public boolean deleteClothingItem(Long id) {
        if (clothingItemRepository.existsById(id)) {
            clothingItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
