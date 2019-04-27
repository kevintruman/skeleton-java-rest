package com.dev.myrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.myrest.model.SysConfig;

public interface SysConfigRequestRepo extends JpaRepository<SysConfig, String> {

	SysConfig findOneByKey(String key);

}
