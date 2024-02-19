package br.com.jps.cervejariaapi.controller;

import br.com.jps.cervejariaapi.business.PurchaseOrderBusiness;
import br.com.jps.cervejariaapi.dto.PurchaseOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderBusiness purchaseOrderBusiness;

    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> findAll() {
        return ResponseEntity.ok(purchaseOrderBusiness.findAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> findPurchaseById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderBusiness.purchaseById(id));
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrderDTO purchaseOrder = purchaseOrderBusiness.newPurchaseOrder(purchaseOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrder);
    }
}
