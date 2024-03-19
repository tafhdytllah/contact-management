package com.tafh.contactmanagement.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tafh.contactmanagement.restful.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
