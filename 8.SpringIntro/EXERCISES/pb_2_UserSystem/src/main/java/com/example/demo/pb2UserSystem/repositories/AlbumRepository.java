package com.example.demo.pb2UserSystem.repositories;

import com.example.demo.pb2UserSystem.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album,Integer> {
}
