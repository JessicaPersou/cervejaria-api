package br.com.jps.cervejariaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 255, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String password;

    @Column(name = "email_client_identifier", length = 100, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String emailClientIdentifier;

    @Column(name = "access_log", columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String accessLog;

    @ManyToOne
    @JoinColumn(name = "email_client_identifier", referencedColumnName = "email", insertable = false, updatable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private Client clientEmail;

}

