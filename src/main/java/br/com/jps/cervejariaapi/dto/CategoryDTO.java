package br.com.jps.cervejariaapi.dto;

import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;
    private String name;
    private Long parentCategory;
}
