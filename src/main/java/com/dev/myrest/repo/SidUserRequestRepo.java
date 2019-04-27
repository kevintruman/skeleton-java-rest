package com.dev.myrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.myrest.model.SidUserRequest;

public interface SidUserRequestRepo extends JpaRepository<SidUserRequest, String> {

	SidUserRequest findOneByUsernameAndPassword(String username, String password);

}
