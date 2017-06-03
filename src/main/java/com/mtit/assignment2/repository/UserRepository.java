package com.mtit.assignment2.repository;

import org.springframework.data.repository.CrudRepository;

import com.mtit.assignment2.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
