package com.g2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2.entity.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

    public abstract User findByUserName(String userName);
}
