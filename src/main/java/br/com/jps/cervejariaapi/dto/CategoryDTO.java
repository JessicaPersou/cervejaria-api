package br.com.jps.cervejariaapi.dto;

import br.com.jps.cervejariaapi.model.Category;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private Long parent;

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.parent = category.getParent().getId();
    }
}
