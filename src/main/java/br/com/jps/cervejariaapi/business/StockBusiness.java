package br.com.jps.cervejariaapi.business;

import br.com.jps.cervejariaapi.dto.StockDTO;

import java.util.List;

public interface StockBusiness {

    List<StockDTO> findAllProductsInStock();
    StockDTO findProductInStockById(Long id);
    StockDTO addNewProductInStock(StockDTO stockDTO);
    StockDTO updateProductInStockById(Long id, StockDTO stockDTO);
    void deleteProductInStockById(Long id);

}
