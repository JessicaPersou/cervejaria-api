package br.com.jps.cervejariaapi.repository;

import br.com.jps.cervejariaapi.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
