package br.com.jps.cervejariaapi.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private Long id;
    private String password;
    private String emailClientIdentifier;
    private String accessLog;
}