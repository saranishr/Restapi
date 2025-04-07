package com.shinchan.shiro.Controller;

import com.shinchan.shiro.Model.Stock;
import com.shinchan.shiro.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public Stock saveStock(@RequestBody Stock stock) {
        return stockService.saveStock(stock);
    }

    @GetMapping
    public Page<Stock> getAllStocks(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return stockService.getAllStocks(page, size);
    }

    @GetMapping("/{id}")
    public Optional<Stock> getStockById(@PathVariable Long id) {
        return stockService.getStockById(id);
    }

    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        return stockService.updateStock(id, stock);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
    }
}
