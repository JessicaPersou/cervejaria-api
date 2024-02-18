package br.com.jps.cervejariaapi.business.businessImpl;

import br.com.jps.cervejariaapi.business.StockBusiness;
import br.com.jps.cervejariaapi.dto.StockDTO;
import br.com.jps.cervejariaapi.exceptions.ErrorMessageException;
import br.com.jps.cervejariaapi.model.Product;
import br.com.jps.cervejariaapi.model.Stock;
import br.com.jps.cervejariaapi.repository.ProductRepository;
import br.com.jps.cervejariaapi.repository.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockBusinessImpl implements StockBusiness {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<StockDTO> findAllProductsInStock() {
        List<StockDTO> listDto = new ArrayList<>();
        List<Stock> stockList = stockRepository.findAll();

        for(Stock stock: stockList){
            StockDTO stockDTO = new StockDTO(stock);
            listDto.add(stockDTO);
        }
        return listDto;
    }

    @Override
    public StockDTO findProductInStockById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.STOCK_NOT_FOUND.getMessage()));
        return new StockDTO(stock);
    }

    @Override
    public StockDTO addNewProductInStock(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setName(stockDTO.getName());
        stock.setQuantityTotal(stockDTO.getQuantityTotal());

        Product product = productRepository.findById(stockDTO.getProductId()).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.PRODUCT_NOT_FOUND.getMessage()));
            if(product != null){
                stock.setProductId(product);
                stockRepository.save(stock);
                stockDTO.setId(stock.getId());
            }
        return new StockDTO(stock);
    }

    @Override
    public StockDTO updateProductInStockById(Long id, StockDTO stockDTO) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.STOCK_NOT_FOUND.getMessage()));

        Product product = productRepository.findById(stockDTO.getProductId()).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.PRODUCT_NOT_FOUND.getMessage()));

        if(product != null){
            BeanUtils.copyProperties(stockDTO, stock, "id");
            stockRepository.save(stock);
        }

        return new StockDTO(stock);
    }

    @Override
    public void deleteProductInStockById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.STOCK_NOT_FOUND.getMessage()));
        if(stock.getQuantityTotal() != 0){
            throw new ErrorMessageException(ErrorMessageException.Message.STOCK_NOT_DELETED.getMessage());
        }
        stockRepository.deleteById(id);
    }
}
