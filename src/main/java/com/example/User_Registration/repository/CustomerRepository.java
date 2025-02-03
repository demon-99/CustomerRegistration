package com.example.User_Registration.repository;

import com.example.User_Registration.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE from Customer c where c.email = :email")
    void deleteUserByEmail(@Param("email") String email);
}
