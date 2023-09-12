package br.com.jps.cervejariaapi.repository;

import br.com.jps.cervejariaapi.dto.ClientDTO;
import br.com.jps.cervejariaapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client as c where c.profileState = c.profileState")
    List<ClientDTO> findClientsByProfileState();

    @Query("select c from Client as c where c.document like %?1%")
    List<ClientDTO> findClientsByDocument(@Param("document") String document);

}
