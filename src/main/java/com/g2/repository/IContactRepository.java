package com.g2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2.entity.Contact;

@Repository("contactRepository")
public interface IContactRepository extends JpaRepository<Contact, Serializable> {

    public abstract Contact findById(int id);
}
