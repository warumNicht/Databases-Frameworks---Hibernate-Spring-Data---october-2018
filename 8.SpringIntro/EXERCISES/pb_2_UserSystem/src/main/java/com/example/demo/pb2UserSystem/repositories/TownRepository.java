package com.example.demo.pb2UserSystem.repositories;

import com.example.demo.pb2UserSystem.models.Town;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends CrudRepository<Town,Integer> {
}
