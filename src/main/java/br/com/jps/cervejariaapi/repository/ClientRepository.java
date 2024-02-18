package br.com.jps.cervejariaapi.repository;

import br.com.jps.cervejariaapi.dto.ClientDTO;
import br.com.jps.cervejariaapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client as c where c.profileState = c.profileState")
    List<ClientDTO> findClientsByProfileState();

    @Query("select c from Client as c where c.document like %?1%")
    List<ClientDTO> findClientsByDocument(@Param("document") String document);

    @Query("select c from Client as c where c.email like %?1%")
    List<ClientDTO> findClientByEmail(@Param("email") String email);

    @Query("select c from Client as c where c.firstName like %?1% order by c.firstName")
    List<ClientDTO> findClientByFirstNameOOrderByFirstName(@Param("name") String name);

}
