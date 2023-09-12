package br.com.jps.cervejariaapi.repository;

import br.com.jps.cervejariaapi.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}
