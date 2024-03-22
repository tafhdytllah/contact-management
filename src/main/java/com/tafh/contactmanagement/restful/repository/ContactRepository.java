package com.tafh.contactmanagement.restful.repository;

import com.tafh.contactmanagement.restful.entity.Contact;
import com.tafh.contactmanagement.restful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    Optional<Contact> findFirstByUserAndId(User user, String id);
}
