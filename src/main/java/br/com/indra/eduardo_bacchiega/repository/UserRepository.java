package br.com.indra.eduardo_bacchiega.repository;

import br.com.indra.eduardo_bacchiega.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
