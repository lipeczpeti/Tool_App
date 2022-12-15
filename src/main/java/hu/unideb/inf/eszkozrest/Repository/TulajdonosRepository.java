package hu.unideb.inf.eszkozrest.Repository;

import hu.unideb.inf.eszkozrest.Entity.TulajdonosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TulajdonosRepository extends JpaRepository<TulajdonosEntity, Long> {
}
