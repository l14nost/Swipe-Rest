package com.example.SwipeRest.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token,Integer> {
    @Query("""
    select t from Token t inner join User a on t.user.idUser = a.idUser
    where a.idUser = :adminId and (t.expired = false or t.revoked = false)
""")
    List<Token> findAllValidTokensByAdmin(Integer adminId);

    Optional<Token> findByToken(String token);
}
