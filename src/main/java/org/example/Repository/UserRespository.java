package org.example.Repository;

import org.example.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRespository extends JpaRepository {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email , Long id);
    Page<User> findByActiveTrue(Pageable pageable);
    List<User> findByActiveTrue();
//    Page<User> findByRoleAndActiveTrue();

}
