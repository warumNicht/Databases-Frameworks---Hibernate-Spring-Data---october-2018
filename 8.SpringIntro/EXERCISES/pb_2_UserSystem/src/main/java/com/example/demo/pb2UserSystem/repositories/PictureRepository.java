package com.example.demo.pb2UserSystem.repositories;

import com.example.demo.pb2UserSystem.models.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends CrudRepository<Picture,Integer> {
}
