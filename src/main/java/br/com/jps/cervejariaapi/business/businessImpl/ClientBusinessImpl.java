package br.com.jps.cervejariaapi.business.businessImpl;

import br.com.jps.cervejariaapi.business.ClientBusiness;
import br.com.jps.cervejariaapi.dto.ClientDTO;
import br.com.jps.cervejariaapi.enums.ProfileState;
import br.com.jps.cervejariaapi.exceptions.ErrorMessageException;
import br.com.jps.cervejariaapi.model.Address;
import br.com.jps.cervejariaapi.model.Client;
import br.com.jps.cervejariaapi.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientBusinessImpl implements ClientBusiness {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> dtoList = new ArrayList<>();

        for (Client client : clients) {
            List<Address> addressList = client.getAddressList();
            ClientDTO clientDTO = new ClientDTO(client, addressList);
            dtoList.add(clientDTO);
        }
        return dtoList;
    }

    @Override
    public ClientDTO clientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + id + "."));
        return new ClientDTO(client, client.getAddressList());
    }

    public List<ClientDTO> clientsByDocument(String document) {
        var client = clientRepository.findClientsByDocument(document);
        if (!client.isEmpty()) {
            return client;
        }
        throw new ErrorMessageException(ErrorMessageException.Message.CLIENT_DOCUMENT_EMPTY.getMessage());
    }

    @Override
    public List<ClientDTO> clientStatusProfileDisabled() {
        return clientRepository.findClientsByProfileState();
    }

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        Client client = new Client();

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setBirthdate(clientDTO.getBirthdate());
        client.setDocument(clientDTO.getDocument());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setDtCreated(LocalDate.now());
        client.setUserRole(clientDTO.getUserRole());
        client.setProfileState(ProfileState.ACTIVE);

        clientRepository.save(client);
        clientDTO.setId(client.getId());

        return new ClientDTO(client);
    }

    @Override
    public ClientDTO clientUpdate(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + id + "."));
        if (client.getProfileState() == ProfileState.DISABLED) {
            throw new ErrorMessageException(ErrorMessageException.Message.CLIENT_PROFILE_DISABLED.getMessage());
        }
        BeanUtils.copyProperties(clientDTO, client, "id", "userRole");
        clientRepository.save(client);

        return new ClientDTO(client);
    }

    @Override
    public ClientDTO disableClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + id + "."));
        if (client.getId() != null) {
            client.setDtProfileDisabled(LocalDate.now());
            client.setProfileState(ProfileState.DISABLED);
        }
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Override
    public List<ClientDTO> findByEmail(String email) {
        if (!email.isEmpty()) {
            return clientRepository.findClientByEmail(email);
        }
        throw new ErrorMessageException(ErrorMessageException.Message.CONTENT_NOT_FOUND.getMessage());
    }

    @Override
    public List<ClientDTO> findByName(String name) {
        if (!name.isEmpty()) {
            return clientRepository.findClientByFirstNameOOrderByFirstName(name);
        }
        throw new ErrorMessageException(ErrorMessageException.Message.CONTENT_NOT_FOUND.getMessage());
    }
}
