package com.example.authservice.repository;
import com.example.authservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
   Optional<Account> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean walletAddressEqualsIgnoreCase(String walletAddress);

    @Query("SELECT u FROM Account u WHERE u.email = :email")
    Account findByEmail(@Param("email") String email);
    @Query("SELECT u FROM Account u WHERE u.walletAddress = :wallet")
    Account findByWallet(@Param("wallet") String wallet);
}
