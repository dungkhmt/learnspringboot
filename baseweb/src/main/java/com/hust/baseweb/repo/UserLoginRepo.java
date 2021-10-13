package com.hust.baseweb.repo;

import com.hust.baseweb.entity.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserLoginRepo extends JpaRepository<UserLogin, String> {
    UserLogin findByUserLoginId(String userLoginId);


    Page<UserLogin> findAll(Pageable page);

    @Query(value = "select * from user_login offset ?1 limit ?2",nativeQuery = true)
    List<UserLogin> findAllQuery(int offset, int limit);

    @Query("select count(*) from user_login")
    int countAllUsers();
}
