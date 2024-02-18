package br.com.jps.cervejariaapi.repository;

import br.com.jps.cervejariaapi.dto.ProductDTO;
import br.com.jps.cervejariaapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name like %?1%")
    List<ProductDTO> findProductByName(@Param("name") String name);
    @Query("select p from Product p where p.categoryId.id = :category")
    List<Product> findProductByCategoryId(@Param("category") Long category);
    @Query("select p from Product p where p.price between :minPrice and :maxPrice order by p.price")
    List<Product> findProductByPriceBetweenOrderByPrice(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
