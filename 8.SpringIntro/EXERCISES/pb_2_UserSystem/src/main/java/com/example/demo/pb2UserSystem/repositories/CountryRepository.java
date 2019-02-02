package com.example.demo.pb2UserSystem.repositories;

import com.example.demo.pb2UserSystem.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country,Integer> {

}
