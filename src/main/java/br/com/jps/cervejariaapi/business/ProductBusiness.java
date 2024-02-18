package br.com.jps.cervejariaapi.business;

import br.com.jps.cervejariaapi.dto.ProductDTO;

import java.util.List;

public interface ProductBusiness {
    List<ProductDTO> allProducts();
    ProductDTO findById(Long id);
    List<ProductDTO> findByPrice(double minPrice, double maxPrice);
    List<ProductDTO> findByCategory(Long category);
    List<ProductDTO> findByName(String name);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
