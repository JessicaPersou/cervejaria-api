package br.com.jps.cervejariaapi.business;

import br.com.jps.cervejariaapi.dto.CategoryDTO;

import java.util.List;

public interface CategoryBusiness {

    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO newCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
