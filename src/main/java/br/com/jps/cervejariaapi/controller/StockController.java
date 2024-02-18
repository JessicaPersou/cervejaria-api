package br.com.jps.cervejariaapi.controller;

import br.com.jps.cervejariaapi.business.StockBusiness;
import br.com.jps.cervejariaapi.dto.StockDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockBusiness stockBusiness;

    @GetMapping
    public ResponseEntity<List<StockDTO>> allProductsInStock(){
        return ResponseEntity.ok(stockBusiness.findAllProductsInStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(stockBusiness.findProductInStockById(id));
    }

    @PostMapping
    public ResponseEntity<StockDTO> addNewProductInStock(@Valid @RequestBody StockDTO stockDTO){
        StockDTO newProduct = stockBusiness.addNewProductInStock(stockDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateProductInStock(@PathVariable Long id, @Valid @RequestBody StockDTO stockDTO){
        return ResponseEntity.ok(stockBusiness.updateProductInStockById(id, stockDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        stockBusiness.deleteProductInStockById(id);
        return ResponseEntity.noContent().build();
    }
}
