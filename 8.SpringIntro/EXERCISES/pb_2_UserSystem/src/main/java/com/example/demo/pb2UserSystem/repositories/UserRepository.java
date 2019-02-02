package com.example.demo.pb2UserSystem.repositories;

import com.example.demo.pb2UserSystem.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> getAllByEmailEndingWith(String domain);

    List<User> getAllByDeletedIsFalseAndLastTimeLoggedInBefore(Date date);
}
