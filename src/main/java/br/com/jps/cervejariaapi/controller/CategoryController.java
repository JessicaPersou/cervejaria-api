package br.com.jps.cervejariaapi.controller;

import br.com.jps.cervejariaapi.business.CategoryBusiness;
import br.com.jps.cervejariaapi.dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryBusiness categoryBusiness;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> allCategorys(){
        return ResponseEntity.ok(categoryBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> categorysById(@PathVariable Long id){
        return ResponseEntity.ok(categoryBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> newCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategory = categoryBusiness.newCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.OK).body(categoryBusiness.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryBusiness.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
