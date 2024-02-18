package br.com.jps.cervejariaapi.controller;

import br.com.jps.cervejariaapi.business.ProductBusiness;
import br.com.jps.cervejariaapi.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductBusiness productBusiness;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> allProducts(){
        return ResponseEntity.ok(productBusiness.allProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(productBusiness.findById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductDTO>> findProductsByName(@Param("name")String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productBusiness.findByName(name));
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductDTO>> findByCategory(@Param("category")Long category) {
        return ResponseEntity.status(HttpStatus.OK).body(productBusiness.findByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductDTO>> findProductsByName(@Param("minPrice")double minPrice, @Param("maxPrice")double maxPrice) {
        return ResponseEntity.status(HttpStatus.OK).body(productBusiness.findByPrice(minPrice, maxPrice));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> registerNewProduct(@Valid @RequestBody ProductDTO productDTO){
        ProductDTO newProduct = productBusiness.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productBusiness.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productBusiness.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
