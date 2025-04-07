package com.shinchan.shiro.Repository;

import com.shinchan.shiro.Model.Stock;
import com.shinchan.shiro.Model.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.product = :product")
    List<Stock> findByProduct(Product product);
}
