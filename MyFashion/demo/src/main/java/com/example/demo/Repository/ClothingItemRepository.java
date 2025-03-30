package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ClothingItem;

public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {

    Page<ClothingItem> findByDescriptionContainingAndSize(String description, String size, Pageable pageable);
    @Modifying
    @Query("SELECT a from ClothingItem a")
    List<ClothingItem> findAll();
}
