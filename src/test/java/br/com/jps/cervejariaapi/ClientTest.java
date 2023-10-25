package br.com.jps.cervejariaapi;

import br.com.jps.cervejariaapi.enums.UserRole;
import br.com.jps.cervejariaapi.model.Client;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.validation.Validator;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ClientTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidClient() {
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setDtCreated(LocalDate.now());
        client.setDocument("12345678901234");
        client.setEmail("john@example.com");
        client.setUserRole(UserRole.USER);

        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidClient() {
        Client client = new Client();
        // Deixando campos em branco e inválidos para fins de teste
        client.setFirstName("");
        client.setLastName("");
        client.setDocument("invalid_document");
        client.setEmail("invalid_email");
        client.setUserRole(null);

        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        // Verifique se há violações de validação esperadas
        Assertions.assertThat(violations).hasSize(5);
    }
}
