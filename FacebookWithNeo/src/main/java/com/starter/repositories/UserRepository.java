package com.starter.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.entity.User;
@Repository
public interface UserRepository extends GraphRepository<User>{
	//User findByName(String name);
}
