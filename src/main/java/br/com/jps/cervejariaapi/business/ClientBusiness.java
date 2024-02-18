package br.com.jps.cervejariaapi.business;

import br.com.jps.cervejariaapi.dto.ClientDTO;

import java.util.List;

public interface ClientBusiness {

    List<ClientDTO> findAll();
    ClientDTO clientById(Long id);
    List<ClientDTO> clientsByDocument(String document);
    List<ClientDTO> clientStatusProfileDisabled();
    ClientDTO createNewClient(ClientDTO clientDTO);
    ClientDTO clientUpdate(Long id, ClientDTO clientDTO);
    ClientDTO disableClient(Long id);
    List<ClientDTO> findByEmail(String email);
    List<ClientDTO> findByName(String name);

}
