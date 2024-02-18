package br.com.jps.cervejariaapi.business;

import br.com.jps.cervejariaapi.dto.AddressDTO;

import java.util.List;

public interface AddressBusiness {

    List<AddressDTO> findAddressByClientId(Long id);
    AddressDTO createNewAddress(Long clientId, AddressDTO addressDTO);
    AddressDTO updateAddress(Long idClient, Long idAddress, AddressDTO addressDTO);
    void deleteAddress(Long idClient, Long idAddress);
}
