package br.com.jps.cervejariaapi.controller;

import br.com.jps.cervejariaapi.business.AddressBusiness;
import br.com.jps.cervejariaapi.business.ClientBusiness;
import br.com.jps.cervejariaapi.dto.AddressDTO;
import br.com.jps.cervejariaapi.dto.ClientDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Api(description = "Endpoints to management of Clients ")
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;

    @Autowired
    private AddressBusiness addressBusiness;

    @GetMapping
    @ApiOperation(httpMethod = "GET", value = "List all Clients", notes = "List all Clients", response = ClientDTO.class)
    public ResponseEntity<List<ClientDTO>> allClients(){
        return ResponseEntity.ok(clientBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> ClientById(@ApiParam("client id") @PathVariable Long id){
        return ResponseEntity.ok(clientBusiness.clientById(id));
    }

    @GetMapping("/document")
    @ApiOperation(httpMethod = "GET", value = "List Clients by Document", notes = "List Clients by Document", response = ClientDTO.class)
    public ResponseEntity<List<ClientDTO>> allClientsByDocument(@ApiParam("Document Number") @Param("document")String document){
        return ResponseEntity.ok(clientBusiness.clientsByDocument(document));
    }

    @ApiOperation(httpMethod = "GET", value = "List Clients by Profile", notes = "List Clients by Profile", response = ClientDTO.class)
    @GetMapping("/profile")
    public ResponseEntity<List<ClientDTO>> clientsProfile(){
        return ResponseEntity.ok(clientBusiness.clientStatusProfileDisabled());
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Created New Client", notes = "Created New Client", response = ClientDTO.class)
    public ResponseEntity<ClientDTO> newClient(@Valid @RequestBody ClientDTO clientDTO){
        ClientDTO newClient = clientBusiness.createNewClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @ApiOperation(httpMethod = "PUT", value = "Update Client", notes = "Update Client", response = ClientDTO.class)
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> clientUpdate(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientBusiness.clientUpdate(id,clientDTO));
    }

    @ApiOperation(httpMethod = "POST", value = "Disable Client", notes = "Disable Client", response = ClientDTO.class)
    @PostMapping ("disabled/{id}")
    public ResponseEntity<ClientDTO> disableClient(@PathVariable Long id){
        return ResponseEntity.ok(clientBusiness.disableClient(id));
    }

    /*** Address-Controller ***/

    @ApiOperation(httpMethod = "GET", value = "List All Addresses", notes = "List All Addresses", response = AddressDTO.class)
    @GetMapping("{id}/address")
    public ResponseEntity<List<AddressDTO>> AddressByClient(@PathVariable Long id){
        return ResponseEntity.ok(addressBusiness.findAddressByClientId(id));
    }

    @ApiOperation(httpMethod = "POST", value = "Create New Address", notes = "Create New Address", response = AddressDTO.class)
    @PostMapping("{clientId}/address/")
    public ResponseEntity<AddressDTO> newAddress(@PathVariable Long clientId, @RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressBusiness.createNewAddress(clientId,addressDTO));
    }

    @ApiOperation(httpMethod = "PUT", value = "Update Address", notes = "Update Address", response = AddressDTO.class)
    @PutMapping("{idClient}/address/{idAddress}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long idClient,@PathVariable Long idAddress,@RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.OK).body(addressBusiness.updateAddress(idClient, idAddress, addressDTO));
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete Address", notes = "Delete Address", response = AddressDTO.class)
    @DeleteMapping("{idClient}/address/{idAddress}")
    public ResponseEntity deleteAddress(@PathVariable Long idClient, @PathVariable Long idAddress){
        addressBusiness.deleteAddress(idClient,idAddress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Endereço excluído com sucesso.");
    }
}
