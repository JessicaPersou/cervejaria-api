package br.com.jps.cervejariaapi.business.businessImpl;

import br.com.jps.cervejariaapi.business.PurchaseOrderBusiness;
import br.com.jps.cervejariaapi.dto.ProductDTO;
import br.com.jps.cervejariaapi.dto.PurchaseOrderDTO;
import br.com.jps.cervejariaapi.enums.ProfileState;
import br.com.jps.cervejariaapi.enums.StatusPurchaseOrder;
import br.com.jps.cervejariaapi.exceptions.ErrorMessageException;
import br.com.jps.cervejariaapi.model.*;
import br.com.jps.cervejariaapi.repository.AddressRepository;
import br.com.jps.cervejariaapi.repository.ClientRepository;
import br.com.jps.cervejariaapi.repository.ProductRepository;
import br.com.jps.cervejariaapi.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderBusinessImpl implements PurchaseOrderBusiness {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<PurchaseOrderDTO> findAllOrders() {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        List<PurchaseOrderDTO> orderDTOS = new ArrayList<>();

        for(PurchaseOrder purchaseOrder: purchaseOrderList){
            List<Product> productList = purchaseOrder.getProductList();
            PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO(purchaseOrder, productList);
            orderDTOS.add(purchaseOrderDTO);
        }
        return orderDTOS;
    }

    @Override
    public PurchaseOrderDTO purchaseById(Long id){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElseThrow(()-> new ErrorMessageException(ErrorMessageException.Message.ORDER_NOT_FOUND.getMessage()));
        List<Product> productList = purchaseOrder.getProductList();
        return new PurchaseOrderDTO(purchaseOrder, productList);
    }

    @Override
    public PurchaseOrderDTO newPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Client client = clientRepository.findById(purchaseOrderDTO.getClientId()).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage()));
        Address address = addressRepository.findById(purchaseOrderDTO.getAddressId()).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.ADDRESS_NOT_FOUND.getMessage()));

        if (client.getProfileState() == ProfileState.ACTIVE) {

            List<Product> productList = new ArrayList<>();
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            double totalPrice = 0;
            int totalQuantity = 0;

            for (ProductDTO productDTO : purchaseOrderDTO.getProductList()) {
                Product product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new ErrorMessageException(
                        ErrorMessageException.Message.PRODUCT_NOT_FOUND.getMessage()));

                    if (product.getQuantity() < productDTO.getQuantity()) {
                        throw new RuntimeException("Quantidade insuficiente do produto " + product.getName());
                    }

                product.setQuantity(product.getQuantity() - productDTO.getQuantity());
                productRepository.save(product);

                productDTO.setId(product.getId());
                productDTO.setName(product.getName());
                productDTO.setDescription(product.getDescription());
                productDTO.setCategoryId(product.getCategoryId().getId());
                totalPrice += product.getPrice();
                productDTO.setPrice(product.getPrice());

                productList.add(product);
                totalQuantity += 1;
            }

            purchaseOrder.setQuantity(totalQuantity);

            DecimalFormat df = new DecimalFormat("#,##0.00");
            String priceFormatDecimal = df.format(totalPrice);
            priceFormatDecimal = priceFormatDecimal.replaceAll("[^\\d.,]", "");

            double formattedPrice = Double.parseDouble(priceFormatDecimal.replace(",", "."));

            purchaseOrder.setTotalPrice(formattedPrice);

            LocalDate date = LocalDate.now();
            purchaseOrder.setDtOrder(date);
            purchaseOrder.setStatus(StatusPurchaseOrder.PENDENT);
            purchaseOrder.setPaymentMethod(purchaseOrderDTO.getPaymentMethod());
            purchaseOrder.setProductList(productList);

            purchaseOrder.setClientId(client);
            purchaseOrder.setAddressId(address);

            PurchaseOrder orderSaved = purchaseOrderRepository.save(purchaseOrder);
            purchaseOrderDTO.setId(orderSaved.getId());
//TODO: Corrigir a quantidade de produtos comprados que exibe na API
            return new PurchaseOrderDTO(purchaseOrder, productList);
        }
        throw new RuntimeException("Cliente inativo");
    }

}


