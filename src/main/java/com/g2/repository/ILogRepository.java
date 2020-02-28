package com.g2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2.entity.Log;

@Repository("logRepository")
public interface ILogRepository extends JpaRepository<Log, Serializable> {

    public abstract Log findById(int id);
}
