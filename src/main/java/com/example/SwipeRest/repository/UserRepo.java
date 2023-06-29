package com.example.SwipeRest.repository;

import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.TypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//
@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>  {
    Optional<User> findByMail(String mail);

    List<User> findAllByMail(String mail);
    List<User> findAllByBlackListIsTrue();
    List<User> findAllByTypeUserAndBlackListIsFalse(TypeUser typeUser);
    Page<User> findAllByBlackListIsTrue(Pageable pageable);

    Page<User> findAllByTypeUserAndBlackListIsFalse(TypeUser typeUser, Pageable pageable);
}
