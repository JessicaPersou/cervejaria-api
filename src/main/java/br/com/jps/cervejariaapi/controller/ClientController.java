package br.com.jps.cervejariaapi.controller;

import br.com.jps.cervejariaapi.business.ClientBusiness;
import br.com.jps.cervejariaapi.dto.ClientDTO;
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

    @GetMapping
    public ResponseEntity<List<ClientDTO>> allClients(){
        return ResponseEntity.ok(clientBusiness.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> ClientById(@PathVariable Long id){
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

    //desativando perfil do client
    @PostMapping ("disabled/{id}")
    public ResponseEntity<ClientDTO> disableClient(@PathVariable Long id){
        return ResponseEntity.ok(clientBusiness.disableClient(id));
    }

}
