package com.demo.syf.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.syf.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	User findByUsername(String username);

}
