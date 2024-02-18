package br.com.jps.cervejariaapi.business.businessImpl;

import br.com.jps.cervejariaapi.business.ProductBusiness;
import br.com.jps.cervejariaapi.dto.ProductDTO;
import br.com.jps.cervejariaapi.exceptions.ErrorMessageException;
import br.com.jps.cervejariaapi.model.Category;
import br.com.jps.cervejariaapi.model.Product;
import br.com.jps.cervejariaapi.repository.CategoryRepository;
import br.com.jps.cervejariaapi.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBusinessImpl implements ProductBusiness {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<ProductDTO> allProducts() {
        List<ProductDTO> dtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        for(Product product: productList){
            ProductDTO productDTO = new ProductDTO(product);
            dtoList.add(productDTO);
        }
        return dtoList;
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ErrorMessageException(ErrorMessageException.Message.PRODUCT_NOT_FOUND.getMessage() + id));
        return new ProductDTO(product);
    }

    @Override
    public List<ProductDTO> findByPrice(double minPrice, double maxPrice) {
        List<Product> productList = productRepository.findProductByPriceBetweenOrderByPrice(minPrice, maxPrice);
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            if (product != null && product.getCategoryId() != null) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(product.getId());
                productDTO.setName(product.getName());
                productDTO.setDescription(product.getDescription());
                productDTO.setPrice(product.getPrice());
                productDTO.setQuantity(product.getQuantity());
                productDTO.setCategoryId(product.getCategoryId().getId());

                productDTOList.add(productDTO);
            }
        }
        return productDTOList;
    }
    public List<ProductDTO> findByCategory(Long categoryId) {
        List<Product> productList = productRepository.findProductByCategoryId(categoryId);
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setCategoryId(product.getCategoryId().getId());

            productDTOList.add(productDTO);
        }

        return productDTOList;
    }




    @Override
    public List<ProductDTO> findByName(String name) {
        if (!name.isEmpty()) {
            return productRepository.findProductByName(name);
        }
        throw new ErrorMessageException(ErrorMessageException.Message.CONTENT_NOT_FOUND.getMessage());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO){
        Product newProduct = new Product();

        newProduct.setName(productDTO.getName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setQuantity(productDTO.getQuantity());

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ErrorMessageException("Categoria não encontrada"));
        newProduct.setCategoryId(category);

        productRepository.save(newProduct);
        productDTO.setId(productDTO.getId());

        return new ProductDTO(newProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.PRODUCT_NOT_FOUND.getMessage() + id));
        if (product.getId() != null) {
            BeanUtils.copyProperties(productDTO, product,"id");
            productRepository.save(product);
        }

        return new ProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.PRODUCT_NOT_FOUND.getMessage() + id));
        if (product.getId() != null) {
            productRepository.deleteById(id);
        }
    }


}
