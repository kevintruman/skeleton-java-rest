package com.dev.myrest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.myrest.model.VwUserMapping;

public interface VwUserMappingRepo extends JpaRepository<VwUserMapping, String> {

	List<VwUserMapping> findByUsername(String username);

}
