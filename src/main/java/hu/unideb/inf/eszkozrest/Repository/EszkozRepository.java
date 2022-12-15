package hu.unideb.inf.eszkozrest.Repository;

import hu.unideb.inf.eszkozrest.Entity.EszkozEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EszkozRepository extends JpaRepository<EszkozEntity, Long> {
}
