package br.com.jps.cervejariaapi.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String zipcode;
    private String neighborhood;
    private String state;
    private String city;
    private String country;
    private Long clientId;
}
