package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumber(String documentNumber);
}
