package com.dev.basic.repository;

import com.dev.basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findUserByMailAndPassword(String mail, String password);
    User findUserByMail(String mail);
    User findUserByUserName (String usrName);
    User findUserByMailAndUserName (String mail, String userName);
}
