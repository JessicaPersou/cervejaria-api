package br.com.jps.cervejariaapi.dto;

import br.com.jps.cervejariaapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String zipcode;
    private String neighborhood;
    private String state;
    private String city;
    private String country;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.zipcode = address.getZipcode();
        this.neighborhood = address.getNeighborhood();
        this.state = address.getState();
        this.city = address.getCity();
        this.country = address.getCountry();
    }

}
