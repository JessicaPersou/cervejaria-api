package br.com.jps.cervejariaapi.repository;

import br.com.jps.cervejariaapi.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
