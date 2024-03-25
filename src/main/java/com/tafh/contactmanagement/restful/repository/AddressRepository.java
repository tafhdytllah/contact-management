package com.tafh.contactmanagement.restful.repository;

import com.tafh.contactmanagement.restful.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
