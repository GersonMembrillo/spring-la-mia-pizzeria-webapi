package org.java.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaRepo extends JpaRepository<Offerta, Integer> {

}
