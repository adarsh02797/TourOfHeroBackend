package com.singh.adarsh.toh.repository;

import com.singh.adarsh.toh.entity.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends MongoRepository<Hero, Integer> {
}
