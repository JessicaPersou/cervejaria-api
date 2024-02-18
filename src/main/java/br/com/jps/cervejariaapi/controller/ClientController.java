package br.com.jps.cervejariaapi.controller;

import br.com.jps.cervejariaapi.business.AddressBusiness;
import br.com.jps.cervejariaapi.business.ClientBusiness;
import br.com.jps.cervejariaapi.dto.AddressDTO;
import br.com.jps.cervejariaapi.dto.ClientDTO;
import br.com.jps.cervejariaapi.exceptions.ErrorMessageException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;

    @Autowired
    private AddressBusiness addressBusiness;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> allClients(){
        return ResponseEntity.ok(clientBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> clientById(@PathVariable Long id){
        return ResponseEntity.ok(clientBusiness.clientById(id));
    }

    @GetMapping("/document")
    public ResponseEntity<List<ClientDTO>> allClientsByDocument(@Param("document")String document){
        return ResponseEntity.ok(clientBusiness.clientsByDocument(document));
    }

    @GetMapping("/profile")
    public ResponseEntity<List<ClientDTO>> clientsProfile(){
        return ResponseEntity.ok(clientBusiness.clientStatusProfileDisabled());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> newClient(@Valid @RequestBody ClientDTO clientDTO){
        ClientDTO newClient = clientBusiness.createNewClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> clientUpdate(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientBusiness.clientUpdate(id,clientDTO));
    }

    @PostMapping ("disabled/{id}")
    public ResponseEntity<ClientDTO> disableClient(@PathVariable Long id){
        return ResponseEntity.ok(clientBusiness.disableClient(id));
    }

    /*** Address-Controller ***/

    @GetMapping("{id}/address")
    public ResponseEntity<List<AddressDTO>> AddressByClient(@PathVariable Long id){
        return ResponseEntity.ok(addressBusiness.findAddressByClientId(id));
    }

    @PostMapping("{clientId}/address/")
    public ResponseEntity<AddressDTO> newAddress(@PathVariable Long clientId, @RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressBusiness.createNewAddress(clientId,addressDTO));
    }

    @PutMapping("{idClient}/address/{idAddress}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long idClient,@PathVariable Long idAddress,@RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.OK).body(addressBusiness.updateAddress(idClient, idAddress, addressDTO));
    }

    @DeleteMapping("{idClient}/address/{idAddress}")
    public ResponseEntity deleteAddress(@PathVariable Long idClient, @PathVariable Long idAddress){
        addressBusiness.deleteAddress(idClient,idAddress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Endereço excluído com sucesso.");
    }

    @GetMapping("/email")
    public ResponseEntity<List<ClientDTO>> findClientsByEmail(@Param("email")String email) {
        return ResponseEntity.status(HttpStatus.OK).body(clientBusiness.findByEmail(email));
    }

    @GetMapping("/name")
    public ResponseEntity<List<ClientDTO>> findClientsByFistName(@Param("name")String name) {
        try{
            if(!name.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(clientBusiness.findByName(name));
            }
        }catch (ErrorMessageException e){
            throw new ErrorMessageException(ErrorMessageException.Message.CONTENT_NOT_FOUND.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
