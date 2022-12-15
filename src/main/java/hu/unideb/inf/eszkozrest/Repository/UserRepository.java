package hu.unideb.inf.eszkozrest.Repository;

import hu.unideb.inf.eszkozrest.Entity.TulajdonosEntity;
import hu.unideb.inf.eszkozrest.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
