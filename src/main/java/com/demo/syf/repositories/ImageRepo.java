package com.demo.syf.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.syf.entities.Image;

@Repository
public interface ImageRepo extends CrudRepository<Image, Long> {

	List<Image> findByUsername(String username);

	Image findByDeleteHash(String hash);

}
