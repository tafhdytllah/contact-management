package com.tafh.contactmanagement.restful.repository;

import com.tafh.contactmanagement.restful.entity.Address;
import com.tafh.contactmanagement.restful.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String>, JpaSpecificationExecutor<Address> {
    Optional<Address> findFirstByContactAndId(Contact contact, String id);

    List<Address> findAllByContact(Contact contact);
}
