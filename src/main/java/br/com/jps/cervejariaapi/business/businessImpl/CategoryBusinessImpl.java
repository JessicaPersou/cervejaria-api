package br.com.jps.cervejariaapi.business.businessImpl;

import br.com.jps.cervejariaapi.business.CategoryBusiness;
import br.com.jps.cervejariaapi.dto.CategoryDTO;
import br.com.jps.cervejariaapi.exceptions.ErrorMessageException;
import br.com.jps.cervejariaapi.model.Category;
import br.com.jps.cervejariaapi.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryBusinessImpl implements CategoryBusiness {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> dtoList = new ArrayList<>();
        List<Category> categoriesList = categoryRepository.findAll();

        for(Category category: categoriesList){
            if(category.getParent() != null){
            CategoryDTO categoryDTO = new CategoryDTO(category);
            dtoList.add(categoryDTO);
            }
        }

        return dtoList;
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CATEGORY_NOT_FOUND.getMessage()));
        return new CategoryDTO(category);
    }

    @Override
    public CategoryDTO newCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParent() != null) {
            Category parentCategory = categoryRepository.findById(categoryDTO.getParent()).orElse(null);
            category.setParent(parentCategory);
        }

        categoryRepository.save(category);
        categoryDTO.setId(category.getId());
        return new CategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CATEGORY_NOT_FOUND.getMessage()));

        if(category.getId() != null){
            BeanUtils.copyProperties(categoryDTO, category, "id");
            categoryRepository.save(category);
            return new CategoryDTO(category);
        }
        throw new ErrorMessageException(ErrorMessageException.Message.CATEGORY_NOT_FOUND.getMessage());
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CATEGORY_NOT_DELETED.getMessage()));

        if(category.getParent().getId() != null){
            categoryRepository.deleteById(id);
        }

    }
}
