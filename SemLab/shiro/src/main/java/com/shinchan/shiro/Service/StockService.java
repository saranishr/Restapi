package com.shinchan.shiro.Service;

import com.shinchan.shiro.Model.Stock;
import com.shinchan.shiro.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

         public Page<Stock> getAllStocks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return stockRepository.findAll(pageable);
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

           public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    public Stock updateStock(Long id, Stock stock) {
        stock.setId(id);
        return stockRepository.save(stock);
    }
}
