package com.dev.myrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.myrest.model.MMappingRequest;

public interface MMappingRequestRepo extends JpaRepository<MMappingRequest, String> {

	MMappingRequest findOneByPath(String path);

}
