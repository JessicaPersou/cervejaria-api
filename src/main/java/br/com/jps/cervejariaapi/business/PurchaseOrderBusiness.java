package br.com.jps.cervejariaapi.business;

import br.com.jps.cervejariaapi.dto.PurchaseOrderDTO;

import java.util.List;

public interface PurchaseOrderBusiness {

    List<PurchaseOrderDTO> findAllOrders();
    PurchaseOrderDTO purchaseById(Long id);
    PurchaseOrderDTO newPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);
}
