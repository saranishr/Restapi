package com.shinchan.shiro.Repository;

import com.shinchan.shiro.Model.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    List<Product> findByNameIgnoreCase(String name);
}
