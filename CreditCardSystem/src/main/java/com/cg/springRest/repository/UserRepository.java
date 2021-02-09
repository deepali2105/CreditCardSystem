package com.cg.springRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.springRest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}